package com.xiaohong.wu.collections.proxy.jdk;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-1 下午6:23
 **/
public class cat implements Animal {


    @Override
    public void say() {
        System.out.println("喵喵");

    }

    @Override
    public void run() {
        System.out.println("冬冬东");
    }
}
