package flab.buynow.order.dto;

import flab.buynow.order.domain.Orders;
import flab.buynow.order.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FindOrderDto {

    private Long id;
    private Long userId;
    private String orderNo;
    private String name;
    private String telephoneNumber;
    private String address;
    private String addressDetail;
    private OrderStatus status;
    private LocalDateTime cancelDate;

    public FindOrderDto(Orders order) {
        this.orderNo = order.getOrderNo();
        this.name = order.getName();
        this.telephoneNumber = order.getTelephoneNumber();
        this.address = order.getAddress();
        this.addressDetail = order.getAddressDetail();
        this.status = order.getStatus();
        this.cancelDate = order.getCancelDate();
    }
}
