package com.test;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicMarkableReference<Object> atomicMarkableReference=new AtomicMarkableReference<>(new Object(),true);
    }
}
