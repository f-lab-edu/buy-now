package flab.buynow.order.controller;

import flab.buynow.common.Util;
import flab.buynow.member.domain.Member;
import flab.buynow.order.domain.OrderItem;
import flab.buynow.order.domain.Orders;
import flab.buynow.order.dto.FindOrderDto;
import flab.buynow.order.dto.FindOrderItemDto;
import flab.buynow.order.dto.InsertOrderDto;
import flab.buynow.order.dto.UpdateOrderDto;
import flab.buynow.order.enums.OrderStatus;
import flab.buynow.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    private final OrderService orderService;

    /**
     * 주문조회
     */
    @GetMapping("/order/{id}")
    public ResponseEntity<FindOrderDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new FindOrderDto(orderService.findById(id)));
    }

    /**
     * 전체 주문조회
     */
    @GetMapping("/orders")
    public ResponseEntity<List<FindOrderDto>> findSliceBy(@RequestParam(defaultValue = "0") long offset,
            @PageableDefault(size=5, sort="id", direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(orderService.findSliceById(offset, pageable).stream().map(FindOrderDto::new).collect(
            Collectors.toList()));
    }

    /**
     * 주문
     */
    @PostMapping("/order")
    public ResponseEntity<FindOrderItemDto> save(@RequestBody @Valid InsertOrderDto order) {
        Orders insertOrder = Orders.builder()
            .member(new Member(order.getUserId()))
            .orderNo(Util.getUuidBasedOnTime())
            .name(order.getName())
            .telephoneNumber(order.getTelephoneNumber())
            .address(order.getAddress())
            .addressDetail(order.getAddressDetail())
            .status(OrderStatus.NORMAL)
            .build();

        OrderItem insertOrderItem = OrderItem.builder()
            .itemId(order.getItemId())
            .quantity(order.getQuantity())
            .order(insertOrder)
            .build();

        return ResponseEntity.ok().body(new FindOrderItemDto(orderService.save(insertOrderItem)));
    }

    /**
     * 주문수정
     */
    @PutMapping("/order/{id}")
    public ResponseEntity<FindOrderDto> update(@PathVariable Long id, @RequestBody @Valid UpdateOrderDto order) {
        Orders updateOrder = Orders.builder()
            .id(id)
            .name(order.getName())
            .telephoneNumber(order.getTelephoneNumber())
            .address(order.getAddress())
            .addressDetail(order.getAddressDetail())
            .build();
        return ResponseEntity.ok().body(new FindOrderDto(orderService.update(id, updateOrder)));
    }

    /**
     * 주문취소
     */
    @PatchMapping("/order/{id}")
    public ResponseEntity<FindOrderDto> cancel(@PathVariable Long id) {
        return ResponseEntity.ok().body(new FindOrderDto(orderService.cancel(id)));
    }

}
