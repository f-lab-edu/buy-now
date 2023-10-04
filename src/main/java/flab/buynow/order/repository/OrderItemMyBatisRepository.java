package flab.buynow.order.repository;

import flab.buynow.order.domain.OrderItem;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderItemMyBatisRepository {

    Optional<OrderItem> findByOrderId(Long orderId);
    int create(OrderItem orderItem);

}
