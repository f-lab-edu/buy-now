package flab.buynow.order.repository;

import flab.buynow.order.domain.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Slice<Orders> findSliceByIdGreaterThan(long offset, Pageable pageable);
}
