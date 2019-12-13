package com.xiaohong.wu.collections.statemachine.abstracts;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:16
 **/
public class StateOpen extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("open");
        context.setState(new StateStep1());
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }
}
