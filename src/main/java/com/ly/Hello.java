package com.ly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Data
class TestA {

    private TestA1 testA1=new TestA1("a1[property]--strong");
    private Map<TestA1,String> mapA1=new HashMap<>();
    private Map<WeakReference<TestA1>,String> mapA2=new HashMap<>();

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("我A被回收啦~");
    }
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class TestA1 {

    private String name;
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("我%s被回收啦~\n",this.name);
    }
}
public class Hello {
    public static void main(String[] args) throws InterruptedException {
        //强引用
        //strongTest();
        //弱引用
        weakTest();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void weakTest(){
        TestA testA =new TestA();
        WeakReference<TestA1> testA1WeakReference=new WeakReference<TestA1>(new TestA1("a1--weak"));
        testA.getMapA2().put(testA1WeakReference,"333");
        TestA1 testA1=new TestA1("a1--strong(weakTest)");

        testA.getMapA1().put(testA1,"3222");
        testA1=null;
        testA =null;
        System.gc();
    }
    private static void strongTest(){
        TestA testA =new TestA();
        TestA1 testA1Key=new TestA1();
        testA.getMapA1().put(testA1Key,"333");
        testA =null;
        System.gc();
    }

}
