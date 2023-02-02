package com.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk代理
 */
@Slf4j
public class LyInvocationHandler implements InvocationHandler {
    private final Object target;

    public LyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("调用前");
        Object result = method.invoke(target, args);
        log.info("调用后");
        return result;
    }

    public static void main(String[] args) {
        ILy ly=new LyImpl();
        ILy ly1 = (ILy)Proxy.newProxyInstance(ly.getClass().getClassLoader(),
                ly.getClass().getInterfaces(), new LyInvocationHandler(ly));
        ly1.say("abc");
    }
}
