package com.xiaohong.wu.collections.jvm;

/**
 * 虚拟机栈和本地方法栈溢出
 * <p>
 * -Xss128k
 * 单线程下，无论是栈帧太大，还是虚拟机栈容量太小，当内存无法再分配时，虚拟机抛出的都是StackOverflowError
 *
 * 多线程情况下每一个线程分配的线程越大，越容易产生内存溢出，主要原因是物理机内存是固定的，把堆内存，方法区内存，等等内存除去后
 * 虚拟机栈个本地方法栈的内存也就相对固定了，每一个线程分配的内存越多，能分配的线程就少了，因此产生了堆存溢出；
 *
 * 这种情况下，就只能减少每一个栈帧的内存容量和减少堆的容量来增加线程数；
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/13 16:59
 **/
public class VMStackOOM {

    private int lenght = 1;

    public void test() {
        lenght++;
        test();
    }

    public static void main(String[] args) {
        VMStackOOM vm = new VMStackOOM();
        try {
            vm.test();
        } catch (Throwable e) {
            System.out.println("stack length:"+ vm.lenght);
            throw e;
        }
    }
}
