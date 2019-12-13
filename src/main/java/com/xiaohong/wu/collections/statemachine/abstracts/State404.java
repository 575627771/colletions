package com.xiaohong.wu.collections.statemachine.abstracts;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:10
 **/
public class State404 extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("状态为404");
    }

    @Override
    public boolean isFinalflag() {
        return true;
    }
}
