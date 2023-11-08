package flab.buynow.order.domain;

import flab.buynow.member.domain.Member;
import flab.buynow.order.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
    private String orderNo;
    private String name;
    private String telephoneNumber;
    private String address;
    private String addressDetail;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;
    @LastModifiedDate
    private LocalDateTime cancelDate;

    public void updateOrder(Orders order) {
        this.name = order.getName();
        this.telephoneNumber = order.getTelephoneNumber();
        this.address = order.getAddress();
        this.addressDetail = order.getAddressDetail();
    }

    public void orderCancel(Long id){
        this.status = OrderStatus.CANCEL;
        this.cancelDate = LocalDateTime.now();
    }

}
