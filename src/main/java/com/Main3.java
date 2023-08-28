package com;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main3 {
    public static void main(String[] args) {
        String s1=new String("abc");
        String s2="abc";
        log.info(String.valueOf(s1==s2));
    }
}
