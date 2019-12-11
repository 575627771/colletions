package com.xiaohong.wu.collections.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-1 下午6:27
 **/
public class CatJDKProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(args);
        Object invoke = method.invoke(proxy, args);
        System.out.println(proxy);


        return invoke;
    }
}
