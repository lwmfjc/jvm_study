package com.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class Person {
    private String name;
    private String phoneNumber;
    // getters and setters

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");


        List<Person> personList=new ArrayList<>();
        personList.add(new Person("a1","123"));
        personList.add(new Person("a2","523"));
        personList.add(new Person("a3",null));
        Map<String, String> collect = personList.stream().collect(Collectors.toMap(
                Person::getName, Person::getPhoneNumber
        ));
        System.out.println(collect);
    }
}
