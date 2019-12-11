package com.xiaohong.wu.collections.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟堆溢出异常
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * <p>
 * dump文件在项目最外层目录里 同时可以指定文件位置
 * <p>
 * 需要判断是内存溢出还是内存泄漏
 * <p>
 * 如果是内存泄漏,可通过工具查看泄漏对象到GC Roots的引用链，定位出泄漏代码的位置
 * <p>
 * 如果不存在泄漏，换句话说，就是内存中的对象确实都必须存活着，那就应该检查虚拟机的堆参数-Xms -Xmx
 * 与机器物理内存对比看是否还可以调大，检查是否存在某些对象的生命周期过长，持有状态时间过长的情况，尝试减少程序运行期的内存消耗。
 *
 * @author Wolf.2
 * @version 1.0
 * @date 2019/8/13 16:05
 **/
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
