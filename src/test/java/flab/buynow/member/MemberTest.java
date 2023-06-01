package flab.buynow.member;

import flab.buynow.member.domain.Member;
import flab.buynow.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback
@Transactional
@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository repository;

    Member newMember;

    @BeforeEach
    void 회원_추가() {
        this.newMember = Member.builder()
                .loginId("testMember")
                .password("0000")
                .name("테스트회원")
                .tel("02-000-0000")
                .address("서울시 강남구 역삼동")
                .addressDetail("123-456")
                .build();

        repository.create(newMember);
    }

    @Test
    void 회원_1명_조회() {
        String existId = this.newMember.getLoginId();
        String notExistId = "notExistId";
        Optional<Member> existMember = repository.findByLoginId(existId);
        Optional<Member> notExistMember = repository.findByLoginId(notExistId);

        assertThat(existMember).isNotNull();
        assertThat(notExistMember).isEmpty();
    }

    @Test
    void 회원가입() {
        assertThat(this.newMember.getLoginId()).isEqualTo("testMember");
        assertThat(this.newMember.getName()).isEqualTo("테스트회원");
    }

    @Test
    void 회원정보수정() {
        this.newMember = Member.builder()
                .loginId(this.newMember.getLoginId())
                .password("1234")
                .name("테스트회원수정")
                .tel("010-0000-0000")
                .build();
        repository.update(this.newMember);
        Optional<Member> updateMember = repository.findByLoginId(this.newMember.getLoginId());

        assertThat(updateMember.get().getPassword()).isEqualTo("1234");
        assertThat(updateMember.get().getName()).isEqualTo("테스트회원수정");
        assertThat(updateMember.get().getTel()).isEqualTo("010-0000-0000");
    }
}