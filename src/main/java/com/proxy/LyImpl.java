package com.proxy;

public class LyImpl implements ILy {
    @Override
    public String say(String word) {
        System.out.println("我说了:"+word);
        return word;
    }
}
