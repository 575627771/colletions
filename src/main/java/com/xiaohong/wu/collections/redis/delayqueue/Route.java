package com.xiaohong.wu.collections.redis.delayqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wu
 * @version 1.0
 * @date 19-1-11 下午7:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private String queue;

    private String list;

}
