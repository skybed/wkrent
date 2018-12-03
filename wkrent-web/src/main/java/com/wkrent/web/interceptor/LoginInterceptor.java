package com.wkrent.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.wkrent.common.entity.base.BaseAjaxVO;
import com.wkrent.common.entity.base.Constants;
import com.wkrent.common.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截
 * @author chengan
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("wkToken");
        log.info("获取当前请求token：", token);
        if(null != token){
            boolean result = JwtUtil.verify(token);
            if(result){
                return true;
            }
        }
        log.warn("当前token解析失败：", token);
        //重置response
        response.reset();
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter pw = response.getWriter();
        BaseAjaxVO baseAjaxVO = new BaseAjaxVO();
        baseAjaxVO.setCode(Constants.TOKEN_AUTH_FAILED);
        baseAjaxVO.setText(Constants.TOKEN_AUTH_FAILED_TEXT);
        pw.write(JSON.toJSONString(baseAjaxVO));

        pw.flush();
        pw.close();
        return false;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
