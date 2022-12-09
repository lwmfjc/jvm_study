package com.test;

public class Student {
    public String name;

    public Student(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println("student's name is : " + name);
    }
}
