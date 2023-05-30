package com;

public class Test1 {
    public static void main(String[] args)   {
        //在代码运行之前，常量池已经有"a","2b","c2d" (还有"java")了
        String  sb1=new StringBuilder("a").append("2b").toString();
        //常量池中"c2d"字符串的引用是否和sb2是一样的，结果是true;因为
        //调用sb1.intern()之前，常量池中没有"a2b",所以现在常量池中的a2b其实就是sb1的引用
        System.out.println(sb1.intern()==sb1);//true
        String  sb2=new StringBuilder("c2d").toString();
        //常量池中"c2d"字符串的引用是否和sb2是一样的，结果是false;因为
        //调用sb2.intern()之前，常量池中就已经有"c2d"
        System.out.println(sb2.intern()==sb2);//false

        //验证上述猜想
        String str1 = new StringBuilder("2").append("b").toString();
        str1.intern();//如果常量池中不存在"2b"，那么就将str1引用保存到常量池;如果存在,则不处理
        System.out.println("2b".intern()==str1);//false 说明常量池中早就存在"2b"字符串了
    }
}
