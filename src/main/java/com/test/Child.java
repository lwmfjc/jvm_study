package com.test;

class A{
    A(){
        System.out.println("A()");
    }
}
public class Child extends Parent{
    private int  i = 1;
    {
        System.out.println(33);
    }
    private A a=new A();
    private int  b = 3;

    Child(){
        System.out.println(b);
    }

    public static void main(String[] args) {
        Child child=new Child();
    }
}
