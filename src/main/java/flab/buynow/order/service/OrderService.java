package flab.buynow.order.service;

import flab.buynow.item.domain.Item;
import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import flab.buynow.order.enums.ItemStatus;
import flab.buynow.order.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Orders findById(Long id) {
        return repository.findById(id);
    }

    public List<Orders> getOrders(PageInfoDto pageInfo) {
        return repository.getOrders(pageInfo);
    }

    @Transactional
    public int create(Orders order) {
        OrderItem orderItem = order.getOrderItem();
        Item item = repository.getItemInfo(orderItem.getItemId());

        repository.insertOrderItem(insertOrderItem(order, orderItem, item));
        return repository.minusStock(minusStock(orderItem, item));
    }

    public int update(Orders order) {
        return repository.update(order);
    }

    @Transactional
    public int cancel(Orders order) {
        repository.cancel(order);
        return repository.plusStock(plusStock(order));
    }

    private OrderItem insertOrderItem(Orders order, OrderItem orderItem, Item item) {
        ItemStatus itemStatus = checkItemStatus(item);
        if(itemStatus.equals(ItemStatus.O)) {
            throw new IllegalStateException("재고가 부족하여 주문할 수 없습니다.");
        }

        repository.insertOrder(order);
        BigDecimal ea = new BigDecimal(order.getOrderItem().getEa());

        return order.getOrderItem().builder()
            .orderId(order.getId())
            .itemId(orderItem.getItemId())
            .ea(orderItem.getEa())
            .price(getTotalPrice(item, itemStatus, ea))
            .build();
    }

    private ItemStatus checkItemStatus(Item item) {
        LocalDateTime now = LocalDateTime.now();

        if(item.getStock() == 0) {
            return ItemStatus.O;
        }

        if(now.compareTo(item.getStartDate()) >= 1 && now.compareTo(item.getEndDate()) <= -1)
            return ItemStatus.S;

        return ItemStatus.N;
    }

    private BigDecimal getTotalPrice(Item item, ItemStatus itemStatus, BigDecimal ea) {
        return ea.multiply(itemStatus.equals(ItemStatus.N) ? item.getPrice() : item.getSalePrice());
    }

    private Item minusStock(OrderItem orderItem, Item item) {
        return Item.builder()
            .id(orderItem.getItemId())
            .stock(item.getStock()-orderItem.getEa())
            .build();
    }

    private Item plusStock(Orders order) {
        OrderItem orderItem = repository.getOrderItem(order.getId());
        Item itemInfo = repository.getItemInfo(orderItem.getItemId());

        return Item.builder()
            .id(orderItem.getItemId())
            .stock(itemInfo.getStock() + orderItem.getEa())
            .build();
    }

}
