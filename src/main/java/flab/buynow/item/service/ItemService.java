package flab.buynow.item.service;

import flab.buynow.item.domain.Item;
import flab.buynow.item.repository.ItemRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 Item은 존재하지 않습니다."));
    }

    public Slice<Item> findSliceById(long offset, Pageable pageable) {
        return itemRepository.findSliceByIdGreaterThan(offset, pageable);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(Long id, Item item) {
        Optional<Item> hasItem = itemRepository.findById(id);
        Item getItem = hasItem.orElseThrow(() -> new IllegalStateException("해당 Item은 존재하지 않습니다."));

        getItem.updateItem(item);
        return item;
    }
}
