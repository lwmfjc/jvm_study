package com.ly;

import java.io.*;

public class MyTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(
                new File(".\\a.txt"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream
        );
        outputStreamWriter.write("hello World!");
        outputStreamWriter.close();
    }
}
