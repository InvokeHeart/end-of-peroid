package org.stop.eop.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.stop.eop.entity.resp.Result;
import org.stop.eop.util.SessionContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // HttpSession session = request.getSession();
        // Object user = session.getAttribute("user");
        SessionContextUtils instance = SessionContextUtils.getInstance();
        String sessionId = request.getHeader("sessionId");
        HttpSession session = instance.getSession(sessionId);
        if (StringUtils.hasText(sessionId) && session != null) {
            if (Objects.nonNull(session.getAttribute("user"))) {
                return true;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(Result.noAuth()));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
