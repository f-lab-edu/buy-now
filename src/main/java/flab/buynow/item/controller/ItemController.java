package flab.buynow.item.controller;

import flab.buynow.item.domain.Item;
import flab.buynow.item.dto.FindItemDto;
import flab.buynow.item.dto.InsertItemDto;
import flab.buynow.item.dto.UpdateItemDto;
import flab.buynow.item.service.ItemService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemService service;

    /**
     * 상품조회
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<FindItemDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(new FindItemDto(service.findById(id)));
    }

    /**
     * 전체상품조회
     */
    @GetMapping("/items")
    public ResponseEntity<List<FindItemDto>> findSliceBy(@RequestParam(defaultValue = "0") long offset,
            @PageableDefault(size=5, sort="id", direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(service.findSliceById(offset, pageable).stream().map(FindItemDto::new).collect(
            Collectors.toList()));
    }

    /**
     * 상품등록
     */
    @PostMapping("/item")
    public ResponseEntity<FindItemDto> create(@RequestBody @Valid InsertItemDto item){
        Item createItem = Item.builder()
                                .name(item.getName())
                                .price(item.getPrice())
                                .salePrice(item.getSalePrice())
                                .stock(item.getStock())
                                .startDate(item.getStartDate())
                                .endDate(item.getEndDate())
                                .build();
        return ResponseEntity.ok().body(new FindItemDto(service.save(createItem)));
    }

    /**
     * 상품수정
     */
    @PutMapping("/item/{id}")
    public ResponseEntity<FindItemDto> update(@PathVariable Long id, @RequestBody @Valid UpdateItemDto item){
        Item updateItem = Item.builder()
                                .id(id)
                                .name(item.getName())
                                .price(item.getPrice())
                                .salePrice(item.getSalePrice())
                                .stock(item.getStock())
                                .startDate(item.getStartDate())
                                .endDate(item.getEndDate())
                                .build();
        return ResponseEntity.ok().body(new FindItemDto(service.update(id, updateItem)));
    }

}
