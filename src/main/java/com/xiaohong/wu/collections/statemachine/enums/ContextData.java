package com.xiaohong.wu.collections.statemachine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Wolf.2
 * @version 1.0
 * @date 2019/12/11 14:42
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContextData {
    //广告位是否有效
    private Boolean valided;
    //是否第一次请求
    private Boolean firsted;
    //是否属于业务线广告位
    private Boolean businessLineed;
    //是否有地域
    private Boolean districted;
}
