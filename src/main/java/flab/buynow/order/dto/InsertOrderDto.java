package flab.buynow.order.dto;

import flab.buynow.order.enums.OrderStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class InsertOrderDto {

    private Long id;
    private Long userId;
    private Long itemId;
    private String orderNo;

    @Positive
    @DecimalMax("100")
    @Digits(integer = 3, fraction = 0)
    @NotNull(message = "빈 값을 입력하실 수 없습니다.")
    private int quantity;

    private BigDecimal price;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 45, message = "최대 45글자까지 입력이 가능합니다.")
    private String name;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Pattern(regexp = "[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}", message = "전화번호 규칙에 맞지 않습니다.")
    private String telephoneNumber;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String address;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String addressDetail;

    private OrderStatus status;

}
