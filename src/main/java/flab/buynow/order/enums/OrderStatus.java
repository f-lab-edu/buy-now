package flab.buynow.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    O("Order"),
    C("Cancel");

    private String orderStatusName;

}
