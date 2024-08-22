package org.example.springBoard.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String memberId = (String) request.getSession().getAttribute("memberId");
        if(memberId == null) {
            throw new AlertException(AlertType.NO_SESSION);
        }
        return true;
    }
}
