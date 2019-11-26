package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "gift_bills")
public class GiftBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders", joinColumns = @JoinColumn(name = "gift_bill_id"), inverseJoinColumns = @JoinColumn(name = "gift_id"))
    private Set<Gift> gift;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private MembershipCard membershipCard;

}
