package flab.buynow.order.repository;

import flab.buynow.item.domain.Item;
import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderRepository {
    Orders findById(Long id);
    List<Orders> getOrders(PageInfoDto pageInfo);
    Item getItemInfo(Long itemId);
    OrderItem getOrderItem(Long orderId);
    long insertOrder(Orders order);
    int insertOrderItem(OrderItem orderItem);
    int update(Orders order);
    int cancel(Orders order);
    int minusStock(Item item);
    int plusStock(Item item);
}
