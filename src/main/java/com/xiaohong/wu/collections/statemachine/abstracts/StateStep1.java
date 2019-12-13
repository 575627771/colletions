package com.xiaohong.wu.collections.statemachine.abstracts;

import com.xiaohong.wu.collections.statemachine.enums.ContextData;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:12
 **/
public class StateStep1 extends State {

    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态为 step1");
        ContextData data = context.getData();
        if (data.getFirsted()) {
            System.out.println("step1--> ");
            //context.setState(new );
        }else {
            System.out.println("step1-->step2");
            context.setState(new StateStep2());
        }
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }
}
