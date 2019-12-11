package com.cinema.cinema.controllers;

import com.cinema.cinema.models.GiftBill;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.repositories.GiftBillRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill-gift")
public class BillGiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftBillRepository giftBillRepository;

    @PostMapping("")
    public String addBillGift(GiftBill giftBill) {
        MembershipCard membershipCard = giftBill.getMembershipCard();
        membershipCard.setPoint(giftBill.getMembershipCard().getPoint() - giftBill.getGift().getPoint());

        memberShipCardRepository.save(membershipCard);
        giftBillRepository.save(giftBill);

        return "fragments/exchange-gift-done";
    }
}
