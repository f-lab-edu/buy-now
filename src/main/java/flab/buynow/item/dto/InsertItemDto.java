package flab.buynow.item.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class InsertItemDto {

    private Long id;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 60, message = "최대 60글자까지 입력이 가능합니다.")
    private String name;

    @Positive
    @DecimalMax("10000000")
    @Digits(integer = 8, fraction = 0)
    @NotNull(message = "빈 값을 입력하실 수 없습니다.")
    private BigDecimal price;

    @Positive
    @DecimalMax("10000000")
    @Digits(integer = 8, fraction = 0)
    private BigDecimal salePrice;

    @Positive
    @NotNull(message = "빈 값을 입력하실 수 없습니다.")
    @Max(value = 1000000, message = "최대 1000000개 이하를 입력하셔야 합니다.")
    private int stock;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
