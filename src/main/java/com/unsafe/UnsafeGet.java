package com.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UnsafeGet {
    private Unsafe unsafe;

    public UnsafeGet() {

        this.unsafe = UnsafeGet.reflectGetUnsafe();
        ;
    }

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void example() throws InterruptedException {
        int size = 4;
        //使用allocateMemory方法申请 4 字节长度的内存空间
        long addr = unsafe.allocateMemory(size);
        //setMemory(Object var1, long var2, long var4, byte var6)
        //从var1的偏移量var2处开始，每个字节都设置为var6，设置var4个字节
        unsafe.setMemory(null, addr, size, (byte) 1);
        //找到一个新的size*2大小的内存块，并且拷贝原来addr的值过来
        long addr3 = unsafe.reallocateMemory(addr, size * 2); //实际操作中这个地址可能等于addr（有概率，没找到原因，这里先假设重新分配了一块）
        System.out.println("addr: " + addr);
        System.out.println("addr3: " + addr3);
        System.out.println("addr值: " + unsafe.getInt(addr));
        System.out.println("addr3值: " + unsafe.getLong(addr3));

        try {
            for (int i = 0; i < 2; i++) {
                // copyMemory(Object var1, long var2, Object var4, long var5, long var7);
                // 从var1的偏移量var2处开始，拷贝数据到var4的偏移量var5上，每次拷贝var7个字节
                //所以i = 0时，拷贝到了addr3的前4个字节；i = 1 时，拷贝到了addr3的后4个字节
                unsafe.copyMemory(null, addr, null, addr3 + size * i, 4);
            }
            System.out.println(unsafe.getInt(addr));
            System.out.println(unsafe.getLong(addr3));
        } finally {
            log.info("start-------");
            unsafe.freeMemory(addr);
            log.info("end-------");
            unsafe.freeMemory(addr3); //实际操作中这句话没执行，不知道原因
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long l = Long.parseLong("0000000100000001000000010000000100000001000000010000000100000001", 2);
        System.out.println(l);
        new UnsafeGet().example();
        /** 输出
         72340172838076673
         addr: 46927104
         addr3: 680731776
         addr值: 16843009
         addr3值: 16843009
         16843009
         72340172838076673
         2023-01-31 14:19:28 下午 [Thread: main]
         INFO:start-------
         */
    }


}
