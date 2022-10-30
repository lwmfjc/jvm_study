package com.ly;

import org.openjdk.jol.info.ClassLayout;

public class Hello4 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable()); //16字节
        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable()); //16字节
    }
}
class Customer{
    private int a;
    private boolean b;
}
