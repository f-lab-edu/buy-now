package flab.buynow.member.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String tel;
    private String address;
    private String addressDetail;
    private LocalDateTime joinDate;
    private LocalDateTime lastUpdatedDate;
    private String adminYn;
}
