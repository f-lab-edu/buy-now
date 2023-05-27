package flab.buynow.member.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PageInfoDto {

    private final Integer pageSize = 5;
    private Integer pageNum;
    private Integer startIndex;

    @Builder
    public PageInfoDto(Integer pageNum) {
        this.pageNum = pageNum - 1;
        this.startIndex = (pageNum - 1) * this.pageSize;
    }

}
