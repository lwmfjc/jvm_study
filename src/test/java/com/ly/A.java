package com.ly;

import lombok.Data;
import lombok.ToString;
import sun.misc.Unsafe;

import java.util.Objects;

@Data
@ToString
public class A extends Object {
    private int a;
    private int b;

    @Override
    public int hashCode() {
        return Objects.hash(a)+Objects.hashCode(b);
    }

    public boolean equals(Object o) {
        // 同一个对象
        if (this == o) {
            return true;
        }
        // o为null
        // 两者是否属于同一个类
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        A obj = (A) o;
        Unsafe unsafe;
        return obj.a * obj.b == this.a * this.b;
    }
}
