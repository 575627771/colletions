package com.xiaohong.wu.collections.statemachine.abstracts;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:15
 **/
public class StateStep2 extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("step2");
        context.setState(new State404());
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }
}
