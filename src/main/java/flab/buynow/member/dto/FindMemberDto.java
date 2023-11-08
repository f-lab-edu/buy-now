package flab.buynow.member.dto;

import flab.buynow.member.domain.Member;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FindMemberDto {
    private Long id;
    private String loginId;
    private String name;
    private LocalDateTime joinDate;
    private boolean adminYn;

    public FindMemberDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.joinDate = member.getJoinDate();
        this.adminYn = member.isAdminYn();
    }
}