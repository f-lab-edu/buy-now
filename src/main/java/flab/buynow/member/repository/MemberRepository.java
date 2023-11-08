package flab.buynow.member.repository;

import flab.buynow.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
    Slice<Member> findSliceByIdGreaterThan(long offset, Pageable pageable);
}