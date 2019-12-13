package com.xiaohong.wu.collections.statemachine.enums;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 14:43
 **/
public class JavaPlatformMachine {

    ContextData data = new ContextData();
    JavaPlatformState state = JavaPlatformState.OPEN;

    //Action
    public void valid() {
        state.valid(this);
    }

    public void first() {
        state.first(this);
    }

    public void businessLine() {
        state.businessLine(this);
    }

    public void district() {
        state.district(this);
    }

    public static void main(String[] args) {
        JavaPlatformMachine pm = new JavaPlatformMachine();
        pm.data.setValided(true);// 广告位是否有效
        pm.data.setFirsted(false);// 是否第一次请求
        pm.data.setBusinessLineed(true);//是否属于业务线广告位
        pm.data.setDistricted(true);//是否有地域
        pm.valid();
        pm.first();
        pm.businessLine();
        pm.district();
    }
}
