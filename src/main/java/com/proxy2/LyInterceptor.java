package com.proxy2;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class LyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("调用前111");
        Object invoke = methodProxy.invokeSuper(o, args);
        log.info("调用后111");
        return invoke;
    }

    public static void main(String[] args) {
        //获取代理类
        Enhancer enhancer=new Enhancer();
        enhancer.setClassLoader(LyInterceptor.class.getClassLoader());
        enhancer.setSuperclass(LyClass.class);
        enhancer.setCallback(new LyInterceptor());
        Object o = enhancer.create();

        LyClass lyClass=(LyClass)o;
        lyClass.say("abc");
        //
    }
}
