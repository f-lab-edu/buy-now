package flab.buynow.item.repository;

import flab.buynow.common.dto.PageInfoDto;
import flab.buynow.item.domain.Item;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemMyBatisRepository {

    Optional<Item> findById(Long id);
    List<Item> findAll(PageInfoDto pageInfo);
    int create(Item item);
    int update(Item item);
    int minusStock(Item item);
    int plusStock(Item item);

}
