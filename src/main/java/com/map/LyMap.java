package com.map;

import java.io.*;
class Student implements Serializable{
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class LyMap {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("a.data"));
        dataOutputStream.writeBoolean(false);
        dataOutputStream.writeFloat(3.0f);
        dataOutputStream.close();
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("a.data"));
        boolean b = dataInputStream.readBoolean();
        System.out.println("第一个:" + b);
        float v = dataInputStream.readFloat();
        System.out.println("第2个" + v);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("a.data"));
        objectOutputStream.writeBoolean(false);
        objectOutputStream.writeFloat(3.0f);
        objectOutputStream.writeObject(new Student("a",10));
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.data"));
        boolean b1 = objectInputStream.readBoolean();
        System.out.println("第一个:" + b1);
        float v2 = objectInputStream.readFloat();
        System.out.println("第2个" + v2);
        Student student=(Student)(objectInputStream.readObject());
        System.out.println(student);

    }
}
