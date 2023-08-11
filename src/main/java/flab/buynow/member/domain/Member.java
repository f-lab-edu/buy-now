package flab.buynow.member.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

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
    private String telephoneNumber;
    private String address;
    private String addressDetail;
    private LocalDateTime joinDate;
    private LocalDateTime lastUpdatedDate;
    private boolean adminYn;
}