package flab.buynow.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDateTime;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String telephoneNumber;
    private String address;
    private String addressDetail;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime joinDate;
    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;
    private boolean adminYn;

    public Member(Long id) {
        this.id = id;
    }

    public void updateMember(Member member) {
        this.password = member.getPassword();
        this.name = member.getName();
        this.telephoneNumber = member.getTelephoneNumber();
        this.address = member.getAddress();
        this.addressDetail = member.getAddressDetail();
    }
}