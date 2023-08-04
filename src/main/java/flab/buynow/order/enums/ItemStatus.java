package flab.buynow.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {

    O("Out of Stock"),
    N("Normal Price Item"),
    S("Sale Price Item");

    private String itemStatusName;

}
