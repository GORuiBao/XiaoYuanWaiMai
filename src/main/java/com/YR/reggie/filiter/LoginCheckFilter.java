package com.YR.reggie.filiter;

import com.YR.reggie.common.BaseContext;
import com.YR.reggie.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Description: 检查用户是否登录
 * date: 2023/4/19 0019 17:14
 *
 * @author YR
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();

        log.info("拦截到请求：{}",requestURI);
        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(requestURI, urls);

        //3、如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //4-1、判断登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            log.info("用户已登录，用户id为：{}",empId);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //4-2、判断移动端客户登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            Long userId = (Long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            log.info("用户已登录，用户id为：{}",userId);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }


        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


//        log.info("拦截到请求：{}",httpServletRequest.getRequestURI());
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 路径匹配，检查本次请求是否放行
     * @param requestURI urls
     * @return
     */
    public boolean check(String requestURI,String[] urls){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
