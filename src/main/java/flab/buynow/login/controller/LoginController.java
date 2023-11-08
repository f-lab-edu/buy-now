package flab.buynow.login.controller;

import flab.buynow.login.dto.LoginMemberDto;
import flab.buynow.login.service.LoginService;
import flab.buynow.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<LoginMemberDto> login(@RequestParam String loginId, @RequestParam String password,
        HttpServletRequest request) {

        Member loginMember = service.login(loginId, password);

        HttpSession session = request.getSession();
        session.setAttribute("loginId", loginMember.getLoginId());
        session.setAttribute("admin", loginMember.isAdminYn());

        return ResponseEntity.ok().body(new LoginMemberDto(loginMember));
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}
