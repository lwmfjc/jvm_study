package com.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

class MyTest{
    private int age ;

    public MyTest(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTest myTest = (MyTest) o;
        return age == myTest.age;
    }

    @Override
    public int hashCode() {
        return age+10000;
    }
}
public class LyMap {
    public static void main(String[] args) {
        Map< MyTest,Integer > map= new LinkedHashMap<>();
        map.put( new MyTest(5),3);
        map.put(new MyTest(5),5);
        map.put(new MyTest(5),67);
        map.put(new MyTest(5),78);
        map.put(new MyTest(5),22);
        map.forEach((i,s)->{
            System.out.println(i);
        });
    }
}
