package com.xiaohong.wu.collections.statemachine.abstracts;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:03
 **/
public abstract class State {

    public abstract void Handle(StateModeContext context );
    public abstract boolean isFinalflag();
}
