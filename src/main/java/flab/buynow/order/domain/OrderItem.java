package flab.buynow.order.domain;

import flab.buynow.item.domain.Item;
import flab.buynow.order.enums.ItemStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@IdClass(OrderItemId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    private Long orderId;
    @Id
    private Long itemId;
    private int quantity;
    private BigDecimal price;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Orders order;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    public OrderItem(Long orderId, OrderItem orderItem, Item item, ItemStatus itemStatus) {
        this.orderId = orderId;
        this.itemId = orderItem.getItemId();
        this.quantity = orderItem.getQuantity();
        this.price = getTotalPrice(item, itemStatus, orderItem.getQuantity());
        this.order = orderItem.getOrder();
        this.item = orderItem.getItem();
    }

    public BigDecimal getTotalPrice(Item item, ItemStatus itemStatus, int ea) {
        return new BigDecimal(ea).multiply(itemStatus.equals(ItemStatus.NO_SALE) ? item.getPrice() : item.getSalePrice());
    }
}
