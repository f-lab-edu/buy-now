package flab.buynow.member.service;

import flab.buynow.member.domain.Member;
import flab.buynow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalStateException("해당 회원은 존재하지 않습니다."));
    }

    public Slice<Member> findSliceById(long offset, Pageable pageable) {
        return memberRepository.findSliceByIdGreaterThan(offset, pageable);
    }

    public Member save(Member member) {
        Optional<Member> hasMember = memberRepository.findByLoginId(member.getLoginId());

        if (!hasMember.isEmpty()) {
            throw new IllegalStateException("ID가 이미 존재합니다.");
        }

        return memberRepository.save(member);
    }

    @Transactional
    public Member update(String loginId, Member member) {
        Optional<Member> hasMember = memberRepository.findByLoginId(loginId);
        Member getMember = hasMember.orElseThrow(() -> new IllegalStateException("해당 회원은 존재하지 않습니다."));

        getMember.updateMember(member);
        return getMember;
    }

}