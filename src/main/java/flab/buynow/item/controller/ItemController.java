package flab.buynow.item.controller;

import flab.buynow.common.dto.PageInfoDto;
import flab.buynow.item.domain.Item;
import flab.buynow.item.dto.InsertItemDto;
import flab.buynow.item.dto.UpdateItemDto;
import flab.buynow.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    /**
     * 전체상품조회
     */
    @GetMapping("/items")
    public ResponseEntity getItems(@RequestParam(defaultValue = "0") long offset) {
        PageInfoDto pageInfo = PageInfoDto.builder().offset(offset).build();
        return ResponseEntity.ok().body(service.getItems(pageInfo));
    }

    /**
     * 상품등록
     */
    @PostMapping("/item")
    public ResponseEntity create(@RequestBody @Valid InsertItemDto item){
        Item createItem = Item.builder()
                                .name(item.getName())
                                .price(item.getPrice())
                                .salePrice(item.getSalePrice())
                                .stock(item.getStock())
                                .startDate(item.getStartDate())
                                .endDate(item.getEndDate())
                                .build();
        return ResponseEntity.ok().body(service.create(createItem));
    }

    /**
     * 상품수정
     */
    @PutMapping("/item/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateItemDto item){
        Item updateItem = Item.builder()
                                .id(id)
                                .name(item.getName())
                                .price(item.getPrice())
                                .salePrice(item.getSalePrice())
                                .stock(item.getStock())
                                .startDate(item.getStartDate())
                                .endDate(item.getEndDate())
                                .build();
        return ResponseEntity.ok().body(service.update(updateItem));
    }

}
