package com.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


public class LyClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //非阻塞
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("客户端连接服务端需要时间,不会阻塞在这,做其他工作");
            }
        }
        System.out.println("客户端连接成功了");

        String str="hello-lyx";//把这段话发给服务端

        System.out.println("等待写入");
        TimeUnit.SECONDS.sleep(5);
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据(将buffer数据写入channel)
        socketChannel.write(buffer);//注意，如果客户端对buffer是写,那么服务端对buffer是读

        System.out.println("客户端连接成功,hashcode:"+socketChannel.hashCode());
        System.out.println("客户端发送成功了");
        //让客户端等待
        System.in.read();

    }
}
