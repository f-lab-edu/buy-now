package flab.buynow.login.service;

import flab.buynow.member.domain.Member;
import flab.buynow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String loginId, String password) {
        Optional<Member> loginMember = memberRepository.findByLoginId(loginId);

        Member member = loginMember.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return loginMember;
    }
}
