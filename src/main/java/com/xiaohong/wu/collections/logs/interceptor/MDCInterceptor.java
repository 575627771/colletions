package com.xiaohong.wu.collections.logs.interceptor;

import com.xiaohong.wu.collections.logs.constans.MdcConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author wu
 * @version 1.0
 * @date 18-12-31 下午5:40
 **/
@Component
public class MDCInterceptor implements HandlerInterceptor {


    private static final Logger LOGGER = LoggerFactory.getLogger(MDCInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----preHandle---");
        MDC.put(MdcConstans.REQUEST_ID,UUID.randomUUID().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("----postHandle---");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("----afterCompletion---");
        MDC.remove(MdcConstans.REQUEST_ID);
    }

}
