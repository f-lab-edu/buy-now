package flab.buynow.order.controller;

import flab.buynow.common.Util;
import flab.buynow.member.dto.PageInfoDto;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import flab.buynow.order.dto.InsertOrderDto;
import flab.buynow.order.enums.OrderStatus;
import flab.buynow.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    /**
     * 주문조회
     */
    @GetMapping("/order/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    /**
     * 전체 주문조회
     */
    @GetMapping("/orders")
    public ResponseEntity findAll(@RequestParam(defaultValue = "0") Long offset) {
        PageInfoDto pageInfo = PageInfoDto.builder().offset(offset).build();
        return ResponseEntity.ok().body(service.findAll(pageInfo));
    }

    /**
     * 주문
     */
    @Transactional
    @PostMapping("/order")
    public ResponseEntity create(@RequestBody @Valid InsertOrderDto order) {
        OrderItem orderItem = OrderItem.builder()
            .itemId(order.getItemId())
            .quantity(order.getQuantity())
            .build();

        Orders insertOrder = Orders.builder()
            .userId(order.getUserId())
            .orderNo(Util.getUuidBasedOnTime())
            .name(order.getName())
            .telephoneNumber(order.getTelephoneNumber())
            .address(order.getAddress())
            .addressDetail(order.getAddressDetail())
            .status(OrderStatus.NORMAL)
            .orderItem(orderItem)
            .build();

        return ResponseEntity.ok().body(service.create(insertOrder));
    }

    /**
     * 주문수정
     */
    @PutMapping("/order/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                @RequestBody @Valid InsertOrderDto order) {
        Orders updateOrder = Orders.builder()
            .id(id)
            .name(order.getName())
            .telephoneNumber(order.getTelephoneNumber())
            .address(order.getAddress())
            .addressDetail(order.getAddressDetail())
            .build();
        return ResponseEntity.ok().body(service.update(updateOrder));
    }

    /**
     * 주문취소
     */
    @PatchMapping("/order/{id}")
    public ResponseEntity cancel(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.cancel(id));
    }

}
