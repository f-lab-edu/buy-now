package flab.buynow.item.dto;

import flab.buynow.item.domain.Item;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FindItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private int stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public FindItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.salePrice = item.getSalePrice();
        this.stock = item.getStock();
        this.startDate = item.getStartDate();
        this.endDate = item.getEndDate();
    }
}