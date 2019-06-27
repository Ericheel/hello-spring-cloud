package com.funtl.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends ZuulFilter {
    /**
     * pre 路由前
     * routing
     * post
     * error
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 路由顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体过滤逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if (token == null) {
            //无token,拒绝
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                HttpServletResponse response = currentContext.getResponse();
                response.setContentType("text/html;charset=utf8");
                response.getWriter().write("非法请求");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
