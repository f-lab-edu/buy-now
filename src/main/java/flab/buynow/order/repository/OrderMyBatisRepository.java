package flab.buynow.order.repository;

import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.order.domain.Orders;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMyBatisRepository {

    Optional<Orders> findById(Long id);
    List<Orders> findAll(PageInfoDto pageInfo);
    long create(Orders order);
    int update(Orders order);
    int cancel(Long id);

}
