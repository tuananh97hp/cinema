package com.cinema.cinema.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "gift_bills")
public class GiftBill {

    public GiftBill() {
    }

    public GiftBill(Set<OrderGift> orderGifts, MembershipCard membershipCard) {
        this.orderGifts = orderGifts;
        this.membershipCard = membershipCard;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "giftBill")
    private Set<OrderGift> orderGifts;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private MembershipCard membershipCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderGift> getOrderGifts() {
        return orderGifts;
    }

    public void setOrderGifts(Set<OrderGift> orderGifts) {
        this.orderGifts = orderGifts;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public int getTotalPoint() {
        return this.orderGifts.stream().mapToInt(OrderGift::getPoint).sum();
    }
}
