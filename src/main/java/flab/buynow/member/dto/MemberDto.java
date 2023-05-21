package flab.buynow.member.dto;

import flab.buynow.common.ValidGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class MemberDto {

    private Long id;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Size(groups = {ValidGroups.createMemberGroup.class},
            min = 1, max = 10, message = "최대 10글자까지 입력이 가능합니다.")
    private String loginId;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Size(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            min = 1, max = 15, message = "최대 15글자까지 입력이 가능합니다.")
    private String password;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Size(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            min = 1, max = 5, message = "최대 5글자까지 입력이 가능합니다.")
    private String name;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Pattern(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            regexp = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}", message = "전화번호 규칙에 맞지 않습니다.")
    private String tel;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Size(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String address;

    @NotBlank(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            message = "빈 값을 입력하실 수 없습니다.")
    @Size(groups = {ValidGroups.createMemberGroup.class, ValidGroups.updateMemberGroup.class},
            min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String addressDetail;

    private LocalDateTime joinDate;

    private LocalDateTime lastUpdatedDate;

    private String adminYn;

}
