package flab.buynow.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {

        HttpSession session = request.getSession(false);
        Boolean admin = (Boolean) session.getAttribute("admin");

        if (admin) {
            log.info("관리자 권한을 가진 회원입니다.");
        } else {
            log.info("일반 회원 권한을 가진 회원입니다.");
        }

        return true;
    }
}
