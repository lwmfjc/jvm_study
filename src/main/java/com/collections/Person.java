package com.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Person {
    private String name;
    private String phoneNumber;
    // getters and setters

    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("abc");
        System.out.println(arrayList);
        Map<String,String> map=new HashMap<>();
    }
}
