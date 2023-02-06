package com.aqs;

import lombok.*;

import java.util.concurrent.atomic.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
class User {
    private String name;
    //如果要为atomicReferenceFieldUpdater服务,必须加上volatile修饰
    public volatile Integer age;
}

public class AtomicTest {
    public static void main(String[] args) {
        System.out.println("原子更新数值---------------");
        AtomicInteger atomicInteger = new AtomicInteger();
        int i1 = atomicInteger.incrementAndGet();
        System.out.println("原子增加后为" + i1);
        System.out.println("原子更新数组---------------");

        int[] a = new int[3];
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(a);

        int i = atomicIntegerArray.addAndGet(1, 3);
        System.out.println("数组元素[" + 1 + "]增加后为" + i);
        System.out.println("数组为" + atomicIntegerArray);
        System.out.println("原子更新对象---------------");
        User user1 = new User("ly1", 10);
        User user2 = new User("ly2", 20);
        User user3 = new User("ly3", 30);
        AtomicReference<User> atomicReference = new AtomicReference<>(user1);
        boolean b = atomicReference.compareAndSet(user2, user3);
        System.out.println("更新" + (b ? "成功" : "失败"));
        System.out.println("引用里值为"+atomicReference.get());
        boolean b1 = atomicReference.compareAndSet(user1, user3);
        System.out.println("更新" + (b1 ? "成功" : "失败"));
        System.out.println("引用里值为"+atomicReference.get());
        System.out.println("原子更新对象属性---------------");
        User user4=new User("ly4",40);
        AtomicReferenceFieldUpdater<User, Integer> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, Integer.class, "age");
        boolean b2 = atomicReferenceFieldUpdater.compareAndSet(user4, 41, 400);
        System.out.println("更新"+(b2?"成功":"失败"));
        System.out.println("引用里user4值为"+atomicReferenceFieldUpdater.get(user4));
        boolean b3 = atomicReferenceFieldUpdater.compareAndSet(user4, 40, 400);
        System.out.println("更新"+(b3?"成功":"失败"));
        System.out.println("引用里user4值为"+atomicReferenceFieldUpdater.get(user4));
        System.out.println("其他使用---------------");
        User user5=new User("ly5",50);
        User user6=new User("ly6",60);
        User user7=new User("ly7",70);
        AtomicMarkableReference<User> userAtomicMarkableReference=new AtomicMarkableReference<>(user5,true);
        boolean b4 = userAtomicMarkableReference.weakCompareAndSet(user6, user7, true, false);
        System.out.println("更新"+(b4?"成功":"失败"));
        System.out.println("引用里值为"+userAtomicMarkableReference.getReference());
        boolean b5 = userAtomicMarkableReference.weakCompareAndSet(user5, user7, false, true);
        System.out.println("更新"+(b5?"成功":"失败"));
        System.out.println("引用里值为"+userAtomicMarkableReference.getReference());
        boolean b6 = userAtomicMarkableReference.weakCompareAndSet(user5, user7, true, false);
        System.out.println("更新"+(b6?"成功":"失败"));
        System.out.println("引用里值为"+userAtomicMarkableReference.getReference());
        System.out.println("AtomicStampedReference使用---------------");
        User user80=new User("ly8",80);
        User user90=new User("ly9",90);
        User user100=new User("ly10",100);
        AtomicStampedReference<User> userAtomicStampedReference=new AtomicStampedReference<>(user80,80);//版本80
        //...每次更改stamp都加1
        //这里假设中途被改成81了
        boolean b7 = userAtomicStampedReference.compareAndSet(user80, user100,81,90);
        System.out.println("更新"+(b7?"成功":"失败"));
        System.out.println("引用里值为"+userAtomicStampedReference.getReference());
        boolean b8 = userAtomicStampedReference.compareAndSet(user80, user100,80,90);
        System.out.println("更新"+(b8?"成功":"失败"));
        System.out.println("引用里值为"+userAtomicStampedReference.getReference());
    }
}
