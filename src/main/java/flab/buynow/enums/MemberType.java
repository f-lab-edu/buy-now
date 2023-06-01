package flab.buynow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {
    ADMIN("Y"),
    MEMBER("N");

    private String adminYn;
}
