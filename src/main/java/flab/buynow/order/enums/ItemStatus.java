package flab.buynow.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {

    NO_STOCK,
    NO_SALE,
    SALE;

}
