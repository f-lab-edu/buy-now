package flab.buynow.login.dto;

import flab.buynow.member.domain.Member;
import lombok.Getter;

@Getter
public class LoginMemberDto {

    private Long id;
    private String loginId;
    private String name;
    private boolean adminYn;

    public LoginMemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.adminYn = member.isAdminYn();
    }
}
