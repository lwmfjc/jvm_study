package com.test;

public class Student {
    public String name;
    public String abc="aaa";

    public Student(String name) {
        this.name = name;
    }

    public void sayName() {
        a();
        b();
        System.out.println("student's name is : " + name);
    }

    public void a(){}

    public void b(){}
}
