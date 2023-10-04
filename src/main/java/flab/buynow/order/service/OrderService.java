package flab.buynow.order.service;

import flab.buynow.item.domain.Item;
import flab.buynow.item.repository.ItemRepository;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import flab.buynow.order.enums.ItemStatus;
import flab.buynow.order.repository.OrderItemRepository;
import flab.buynow.order.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public Orders findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 주문은 존재하지 않습니다."));
    }

    public Slice<Orders> findSliceById(long offset, Pageable pageable) {
        return orderRepository.findSliceByIdGreaterThan(offset, pageable);
    }

    @Transactional
    public OrderItem save(OrderItem orderItem) {
        Item item = itemRepository.findById(orderItem.getItemId())
            .orElseThrow(() -> new IllegalStateException("해당 상품은 존재하지 않습니다."));

        ItemStatus itemStatus = checkItemStatus(item, orderItem.getQuantity());
        if(itemStatus.equals(ItemStatus.NO_STOCK)) {
            throw new IllegalStateException("재고가 부족하여 주문할 수 없습니다.");
        }

        Orders saveOrder = orderRepository.save(orderItem.getOrder());
        orderItemRepository.save(new OrderItem(saveOrder.getId(), orderItem, item, itemStatus));
        item.minusStock(orderItem.getQuantity());

        return orderItem;
    }

    @Transactional
    public Orders update(Long id, Orders order) {
        Optional<Orders> hasOrder = orderRepository.findById(id);
        Orders getOrder = hasOrder.orElseThrow(() -> new IllegalStateException("해당 주문은 존재하지 않습니다."));

        getOrder.updateOrder(order);
        return order;
    }

    @Transactional
    public Orders cancel(Long id) {
        OrderItem orderItem = orderItemRepository.findByOrderId(id)
            .orElseThrow(() -> new IllegalStateException("해당 주문은 존재하지 않습니다."));

        orderItem.getOrder().orderCancel(id);
        orderItem.getItem().plusStock(orderItem.getQuantity());

        return orderItem.getOrder();
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

}
