package flab.buynow.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageInfoDto {

    private Integer limit;
    private Integer lastId;

}
