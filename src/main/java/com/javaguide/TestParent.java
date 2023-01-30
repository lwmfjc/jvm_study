package com.javaguide;

import java.io.IOException;

public class TestParent {
    protected static void ab(){}

    private String a;

    protected AParent x() {
        return new AParent();
    }

    protected void b() throws Exception {
    }

}

class TestChild extends TestParent {

    public static void ab(){}

    public String a;

    /**
     * 返回类型有误，没有比父类更具体
     *
     * @return
     */
   /* protected AParentParent x() {
        return new AChild();
    }*/
    protected AChild x() {
        return new AChild();
    }

    /**
     * 抛异常类型有误 没有比父类更具体
     *
     * @throws Throwable
     */
    /*protected void b() throws Throwable {
    }*/
    protected void b() throws IOException {
    }

}

