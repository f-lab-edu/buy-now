package flab.buynow.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfoDto {

    public static int SIZE = 5;
    private final int limit = 5;
    private long offset;

}
