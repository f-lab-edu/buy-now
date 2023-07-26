package flab.buynow.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfoDto {

    private final int limit = 5;
    private long offset;

}
