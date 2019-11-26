package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "gift_bills")
public class GiftBill {

    public GiftBill() {
    }

    public GiftBill(Gift gift, MembershipCard membershipCard) {
        this.gift = gift;
        this.membershipCard = membershipCard;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "orders", joinColumns = @JoinColumn(name = "gift_bill_id"), inverseJoinColumns = @JoinColumn(name = "gift_id"))
    private Gift gift;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private MembershipCard membershipCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }
}
