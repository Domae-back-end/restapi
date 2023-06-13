package com.restfulapi.restfulapi.domain.entity;

import com.restfulapi.restfulapi.domain.request.ShopSaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "shopName"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "registeredAt")
})
@Entity
public class Shop {

    @Id
    private String shop_pid;

    @Column
    private String shopName;

    @Column
    private String shopAddress;

    @Column
    private String shopPhoneNumber;

    @Column
    private String hashtag;

    @OrderBy("registeredAt DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Food> foodList;

    @OrderBy("registeredAt DESC")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ShopComment> shopCommentList;

    @Column
    private Timestamp registeredAt;
    @Column
    private Timestamp updatedAt;

    protected Shop() {

    }

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    private Shop(String shop_pid, String shopName, String shopAddress, String shopPhoneNumber, String hashtag) {
        this.shop_pid = shop_pid;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopPhoneNumber = shopPhoneNumber;
        this.hashtag = hashtag;
    }

    public static Shop requestIntoShop(ShopSaveRequest req, String shop_pid){
        return new Shop(
                shop_pid,
                req.shopName(),
                req.shopAddress(),
                req.shopPhoneNumber(),
                req.hashtag()
        );
    }


}
