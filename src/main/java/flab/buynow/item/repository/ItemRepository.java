package flab.buynow.item.repository;

import flab.buynow.item.domain.Item;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Item> findById(Long id);
    Slice<Item> findSliceByIdGreaterThan(long offset, Pageable pageable);
}
