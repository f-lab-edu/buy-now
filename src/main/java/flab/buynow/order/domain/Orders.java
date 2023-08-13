package flab.buynow.order.domain;

import flab.buynow.order.enums.OrderStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    private Long id;
    private Long userId;
    private String orderNo;
    private String name;
    private String telephoneNumber;
    private String address;
    private String addressDetail;
    private OrderStatus status;
    private LocalDateTime cancelDate;
    
    private OrderItem orderItem;

}
