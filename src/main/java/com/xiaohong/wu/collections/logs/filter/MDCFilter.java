package com.xiaohong.wu.collections.logs.filter;

import com.xiaohong.wu.collections.logs.constans.MdcConstans;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author wu
 * @version 1.0
 * @date 18-12-31 下午5:52
 **/
@Component
@WebFilter(urlPatterns = "/*")
public class MDCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(MdcConstans.REQUEST_ID, UUID.randomUUID().toString());
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(MdcConstans.REQUEST_ID);
        }
    }
}
