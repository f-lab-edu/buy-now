package flab.buynow.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateMemberDto {

    private Long id;
    private String loginId;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 15, message = "최대 15글자까지 입력이 가능합니다.")
    private String password;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 5, message = "최대 5글자까지 입력이 가능합니다.")
    private String name;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Pattern(regexp = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}", message = "전화번호 규칙에 맞지 않습니다.")
    private String tel;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String address;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String addressDetail;

    private LocalDateTime joinDate;
    private LocalDateTime lastUpdatedDate;
    private String adminYn;

}