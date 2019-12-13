package com.xiaohong.wu.collections.statemachine.abstracts;

import com.xiaohong.wu.collections.statemachine.enums.ContextData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 15:08
 **/
@Getter
@Setter
@NoArgsConstructor

public class StateModeContext {

    private State state;
    private ContextData data ;

    public StateModeContext(State state, ContextData data) {
        this.state = state;
        this.data = data;
    }

    /// 对请求做处理，并设置下一个状态

    boolean  trueFlag = true;

    public void Request()
    {
        //如果当前step 是最后一步  nextStep 不执行
        if(state.isFinalflag()){
            trueFlag = false;
        }
        state.Handle(this);
    }

    public static void main(String[] args) {
        // 设置Context的初始状态为ConcreteStateA
        ContextData data = new ContextData(true,false,true,true);
        StateModeContext context = new StateModeContext(new StateOpen(),data);
        // 不断地进行请求，同时更改状态
        for(;;){
            if(context.trueFlag){
                context.Request();
            }else {
                break;
            }
        }
    }
}
