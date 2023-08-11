package flab.buynow.item.service;

import flab.buynow.common.dto.PageInfoDto;
import flab.buynow.item.domain.Item;
import flab.buynow.item.repository.ItemRepository;
import flab.buynow.order.enums.ItemStatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    public List<Item> getItems(PageInfoDto pageInfo) {
        return repository.getItems(pageInfo);
    }

    public int create(Item item) {
        return repository.create(item);
    }

    public int update(Item item) {
        return repository.update(item);
    }

    public BigDecimal getTotalPrice(Item item, ItemStatus itemStatus, BigDecimal ea) {
        return ea.multiply(itemStatus.equals(ItemStatus.NO_SALE) ? item.getPrice() : item.getSalePrice());
    }
}
