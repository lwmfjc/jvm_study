package com.clone;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Data
class Sheep implements Serializable {
    String name;
    int age;
    Sheep friend;

    public Sheep(String name, int age, Sheep friend) {
        this.name = name;
        this.age = age;
        this.friend = friend;
    }

    public Sheep(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object deepClone() {
        //输出流
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        //输入流
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); //当前对象以对象流方式输出

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            Sheep sheep = (Sheep) ois.readObject();
            return sheep;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }
}

@Slf4j
public class MyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep sheep = new Sheep("六一", 12);
        sheep.friend = new Sheep("朋友", 10);
        Sheep clone = (Sheep) sheep.deepClone();
        log.info(clone.toString());
        log.info(String.valueOf(sheep.friend == clone.friend));

    }
}
