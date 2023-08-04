package flab.buynow.order.domain;

import flab.buynow.order.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    private Long id;
    private Long userId;
    private String orderNo;
    private String name;
    private String tel;
    private String address;
    private String addressDetail;
    private OrderStatus status;
    
    private OrderItem orderItem;

}
