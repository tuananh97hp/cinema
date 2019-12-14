package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    public Order() {
    }

    public Order(Gift gift, int quantity, int point) {
        this.gift = gift;
        this.quantity = quantity;
        this.point = point;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "gift_bill_id")
    private GiftBill giftBill;

    @Id
    @ManyToOne
    @JoinColumn(name = "gift_id")
    private Gift gift;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "point")
    private int point;

    public GiftBill getGiftBill() {
        return giftBill;
    }

    public void setGiftBill(GiftBill giftBill) {
        this.giftBill = giftBill;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
