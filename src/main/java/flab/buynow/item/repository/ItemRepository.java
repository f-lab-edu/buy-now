package flab.buynow.item.repository;

import flab.buynow.common.dto.PageInfoDto;
import flab.buynow.item.domain.Item;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemRepository {

    Optional<Item> findById(Long id);
    List<Item> getItems(PageInfoDto pageInfo);
    int create(Item item);
    int update(Item item);

}
