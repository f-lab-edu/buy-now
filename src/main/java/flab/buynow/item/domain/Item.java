package flab.buynow.item.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal salePrice;
    private int stock;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void updateItem(Item item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.salePrice = item.getSalePrice();
        this.stock = item.getStock();
        this.startDate = item.getStartDate();
        this.endDate = item.getEndDate();
    }

    public void plusStock(int stock) {
        this.stock += stock;
    }

    public void minusStock(int stock) {
        this.stock -= stock;
    }

}
