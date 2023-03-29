package com;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

interface ILy {
    String say(String word);
}

class LyImpl implements ILy{

    @Override
    public String say(String word) {
        return "hello ,"+word;
    }
}

@Slf4j
class MyInvocationHandler implements InvocationHandler {
    private final Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("调用前");
        Object result = method.invoke(target, args);
        log.info("结果是:"+result);
        log.info("调用后");
        return result;
    }
}
//cglib动态代理
@Slf4j
class MyCglibProxyInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("调用前");
        //注意，这里是invokeSuper，如果是invoke就会调用自己，导致死循环(递归)
        Object result = methodProxy.invokeSuper(o, args);
        log.info(o.getClass().getSuperclass().getTypeName());
        Class<?>[] interfaces = o.getClass().getSuperclass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            log.info("接口名:"+anInterface.getTypeName());
        }
        log.info("调用结果"+result);
        log.info("调用后");
        return result;
    }
}

@Slf4j
public class Test {
    String a;

    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setClassLoader(Test.class.getClassLoader());
        enhancer.setSuperclass(LyImpl.class);
        enhancer.setCallback(new MyCglibProxyInterceptor());
        //方法一(通过)
        ILy o = (ILy)enhancer.create();
        //方法二(通过)
        //LyImpl o = (LyImpl)enhancer.create();
        o.say("lyly");
        log.info(Arrays.toString(LyImpl.class.getInterfaces()));
        /*LyImpl target = new LyImpl();
        ILy targetProxy = (ILy)Proxy.newProxyInstance(Test.class.getClassLoader(),
                target.getClass().getInterfaces(), new MyInvocationHandler(target));
        targetProxy.say("dxs");*/
    }
}
