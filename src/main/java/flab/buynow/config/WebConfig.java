package flab.buynow.config;

import flab.buynow.interceptor.AuthorizationInterceptor;
import flab.buynow.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 로그인 여부 확인과 admin 권한 여부 체크
         * 원활한 테스트를 위하여 해당 interceptors는 임시 주석 처리
         * 1) 로그인 여부(session)에 따라 session == null일 경우, 로그인 재요청
         * 2) admin 권한 여부(adminYn)에 따라 api 요청 또는 페이지 권한 부여
         *    현재는 관리자/일반회원 여부만 1차 확인 후 별다른 권한은 부여하지 않음
         */
        /*
        registry.addInterceptor(loginInterceptor)
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/login", "/logout");

        registry.addInterceptor(authorizationInterceptor)
            .order(2)
            .addPathPatterns("/**")
            .excludePathPatterns("/login", "/logout");
         */
    }
}
