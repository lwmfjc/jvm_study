package com;
interface Node<T>{
    public void setData(T data) ;
}
public class MyNode implements Node<Integer> {
    @Override
    public void setData(Integer data) {

    }
    static String a;

    public static void main(String[] args) {
        System.out.println(a);
    }
}
