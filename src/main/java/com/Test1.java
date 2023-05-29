package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;

class T1{}
class T2{}
public class Test1 {
    public static void main(String[] args) throws SQLException {
        /*Object t1=new T1();
        T2 t2=(T2)t1;//java.lang.ClassCastException
        */
        /*Thread thread=new Thread(()->{
            System.out.println("hello");
        });
        thread.start();
        thread.start();*/
        DriverManager.getDriver("com.MyDriver");
    }
}
