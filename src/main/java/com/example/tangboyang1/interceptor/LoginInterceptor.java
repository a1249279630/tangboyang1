package com.example.tangboyang1.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tangboyang1.dao.LoginDao;
import com.example.tangboyang1.dto.ResultDTO;
import com.example.tangboyang1.error.CommonErrorCode;
import com.example.tangboyang1.pojo.User;
import com.example.tangboyang1.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by codedrinker on 2018/12/2.
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) factory.getBean("stringRedisTemplate");
        try{
            //请求之前，验证通过返回true，验证失败返回false
            String token = request.getHeader("token");
            String value=stringRedisTemplate.opsForValue().get(token);
            if (StringUtils.isEmpty(value)) {
                makeFail(response);
                return false;
            }
            User user = JSONObject.parseObject(value, User.class);
            SessionUtil.setUser(user);
            return true;

        }catch (Exception e){
            makeFail(response);
            return false;
        }
    }
    // 通过 token 从数据库中获取信息，如果没有验证失败
    // 如果通过一台设备登录，再通过另一台设备登录，第一台设备会自动登出
    private void makeFail(HttpServletResponse response) {
        ResultDTO resultDTO = ResultDTO.fail(CommonErrorCode.Token_Errop);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(resultDTO));
            out.close();
        } catch (Exception e) {
            log.error("LoginInterceptor preHandle", e);
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //请求结束
        //请求结束以后移除 user
        SessionUtil.removeUser();
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
