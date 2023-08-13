package flab.buynow.order.service;

import flab.buynow.item.domain.Item;
import flab.buynow.item.repository.ItemRepository;
import flab.buynow.item.service.ItemService;
import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import flab.buynow.order.enums.ItemStatus;
import flab.buynow.order.repository.OrderItemRepository;
import flab.buynow.order.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemService itemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Orders> findAll(PageInfoDto pageInfo) {
        return orderRepository.findAll(pageInfo);
    }

    @Transactional
    public int create(Orders order) {
        OrderItem orderItem = order.getOrderItem();
        Item item = itemRepository.findById(orderItem.getItemId())
            .orElseThrow(() -> new IllegalStateException("해당 상품은 존재하지 않습니다."));

        orderItemRepository.create(insertOrderItem(order, orderItem, item));
        return itemRepository.minusStock(minusStock(orderItem));
    }

    public int update(Orders order) {
        return orderRepository.update(order);
    }

    @Transactional
    public int cancel(Long id) {
        OrderItem orderItem = orderItemRepository.findByOrderId(id)
            .orElseThrow(() -> new IllegalStateException("해당 주문은 존재하지 않습니다."));

        orderRepository.cancel(id);
        return itemRepository.plusStock(plusStock(orderItem));
    }

    private OrderItem insertOrderItem(Orders order, OrderItem orderItem, Item item) {
        ItemStatus itemStatus = checkItemStatus(item, orderItem.getQuantity());
        if(itemStatus.equals(ItemStatus.NO_STOCK)) {
            throw new IllegalStateException("재고가 부족하여 주문할 수 없습니다.");
        }

        orderRepository.create(order);
        BigDecimal ea = new BigDecimal(order.getOrderItem().getQuantity());

        return order.getOrderItem().builder()
            .orderId(order.getId())
            .itemId(orderItem.getItemId())
            .quantity(orderItem.getQuantity())
            .price(itemService.getTotalPrice(item, itemStatus, ea))
            .build();
    }

    private ItemStatus checkItemStatus(Item item, int quantity) {
        LocalDateTime now = LocalDateTime.now();

        if(item.getStock() == 0 || item.getStock() < quantity) {
            return ItemStatus.NO_STOCK;
        }

        if(now.isBefore(item.getEndDate()) && now.isAfter(item.getStartDate())) {
            return ItemStatus.SALE;
        }

        return ItemStatus.NO_SALE;
    }

    private Item minusStock(OrderItem orderItem) {
        return Item.builder()
            .id(orderItem.getItemId())
            .stock(orderItem.getQuantity())
            .build();
    }

    private Item plusStock(OrderItem orderItem) {
        return Item.builder()
            .id(orderItem.getItemId())
            .stock(orderItem.getQuantity())
            .build();
    }

}
