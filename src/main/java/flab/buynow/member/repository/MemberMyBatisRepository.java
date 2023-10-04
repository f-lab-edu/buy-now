package flab.buynow.member.repository;

import flab.buynow.member.domain.Member;
import flab.buynow.member.dto.PageInfoDto;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMyBatisRepository {

    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll(PageInfoDto pageInfo);
    int create(Member member);
    int update(Member member);
}