package flab.buynow.member.repository;

import flab.buynow.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface MemberRepository {

    Optional<Member> findByLoginId(String loginId);

    List<Member> getMembers();

    int create(Member member);

    int update(Member member);
}