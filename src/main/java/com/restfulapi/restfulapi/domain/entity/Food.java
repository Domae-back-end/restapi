package com.restfulapi.restfulapi.domain.entity;

import com.restfulapi.restfulapi.domain.request.FoodSaveRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Food {

    @Id
    private String food_pid;

    @ManyToOne(optional = false)
    private Shop shop;

    @Column
    private String foodName;

    @Column
    private int foodPrice;

    @Column
    private String foodContent;

    @Column
    private String img;

    @Column
    private Timestamp registeredAt;
    @Column
    private Timestamp updatedAt;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    protected Food() {
    }

    private Food(String food_pid, Shop shop, String foodName, int foodPrice, String foodContent, String img) {
        this.food_pid = food_pid;
        this.shop = shop;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodContent = foodContent;
        this.img = img;
    }

    public static Food requestIntoFood(FoodSaveRequest req, String foodPid, Shop shop, String img) {
        return new Food(
                foodPid,
                shop,
                req.foodName(),
                req.foodPrice(),
                req.foodContent(),
                img
        );
    }
}
