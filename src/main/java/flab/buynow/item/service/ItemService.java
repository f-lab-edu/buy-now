package flab.buynow.item.service;

import flab.buynow.common.dto.PageInfoDto;
import flab.buynow.item.domain.Item;
import flab.buynow.item.repository.ItemRepository;
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

    public List<Item> findAll(PageInfoDto pageInfo) {
        return repository.findAll(pageInfo);
    }

    public int create(Item item) {
        return repository.create(item);
    }

    public int update(Item item) {
        return repository.update(item);
    }

}
