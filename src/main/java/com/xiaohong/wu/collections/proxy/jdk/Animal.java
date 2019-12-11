package com.xiaohong.wu.collections.proxy.jdk;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-1 下午6:21
 **/
public interface Animal {

    default void say(){
        System.out.println("动物要说话");
    }

    default void run(){
        System.out.println("跑起来-----");
    }
}
