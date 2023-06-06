package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class LyServer {
    public static void main(String[] args) throws IOException {

        //得到Channel ->ServerSocket
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();

        //得到Selector对象
        Selector selector=Selector.open();

        //绑定端口6666
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

        //这个通道(serverSocketChannel)用来接收客户端的请求，关心事件为ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){

            //如果一秒内没有事件发生
            while (selector.select(1000)==0){
                System.out.println("服务端等待了1秒，没有连接");
                continue;
            }

            //如果返回的不是0（有事件)，获取到相关的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历集合
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件做不同处理

                //如果是连接事件
                if(key.isAcceptable()){
                    //给该客户端生成SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功,hashcode:"+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    //注册,并说明关注事件,同时给SocketChannel关联一个Buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("服务端连接处理完毕");
                }else {
                    //其他事件
                    //如果是要读取数据
                    if(key.isReadable()){ //发生OP_READ
                        //通过key反向获取到对应channel
                        SocketChannel channel=(SocketChannel) key.channel();
                        //获取到该channel关联的Buffer
                        ByteBuffer buffer=(ByteBuffer)key.attachment();
                        channel.read(buffer);
                        //注意,这里会读到1024个字节,因为一开始分配给buffer1024个
                        System.out.println("from client +"+new String(buffer.array(), StandardCharsets.UTF_8));
                    }
                }
                //不要写错位置,不然会把某些事件漏移除，导致异常
                //从集合中移除当前的selectionKey,防止重复操作
                keyIterator.remove();
            }

        }
    }
}
