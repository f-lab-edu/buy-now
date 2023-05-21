package flab.buynow.member.service;

import flab.buynow.member.domain.Member;
import flab.buynow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Optional<Member> findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    public List<Member> getMembers() {
        return repository.getMembers();
    }

    public int create(Member member) {
        Optional<Member> hasMember = repository.findByLoginId(member.getLoginId());

        if(!hasMember.isEmpty())
            throw new IllegalStateException("ID가 이미 존재합니다.");

        return repository.create(member);
    }

    public int update(Member member) {
        return repository.update(member);
    }

}