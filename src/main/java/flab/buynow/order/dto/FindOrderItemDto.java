package flab.buynow.order.dto;

import flab.buynow.order.domain.OrderItem;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class FindOrderItemDto {

    private Long orderId;
    private Long itemId;
    private int quantity;
    private BigDecimal price;

    public FindOrderItemDto(OrderItem orderItem) {
        this.orderId = orderItem.getOrderId();
        this.itemId = orderItem.getItemId();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
