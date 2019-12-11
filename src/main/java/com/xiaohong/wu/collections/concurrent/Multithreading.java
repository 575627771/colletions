package com.xiaohong.wu.collections.concurrent;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/21 10:59
 **/
public class Multithreading {

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new DefaultThreadFactory(Multithreading.class);
        ExecutorService singleThreadPool = new ThreadPoolExecutor(4, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(8), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < in.length; i++) {
            int finalI = i;
            singleThreadPool.execute(() -> print(Thread.currentThread().getName()+String.valueOf(in[finalI])));
        }
        singleThreadPool.shutdown();
    }

    public static void print(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name);
    }
}
