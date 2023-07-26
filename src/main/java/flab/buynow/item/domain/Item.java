package flab.buynow.item.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private int stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
