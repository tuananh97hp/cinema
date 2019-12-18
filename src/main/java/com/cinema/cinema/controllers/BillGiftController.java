package com.cinema.cinema.controllers;

import com.cinema.cinema.models.GiftBill;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.repositories.GiftBillRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bill-gift")
public class BillGiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftBillRepository giftBillRepository;

    @PostMapping("")
    public String addBillGift(HttpSession httpSession) {
        GiftBill giftBill = (GiftBill) httpSession.getAttribute("giftBill");
        giftBillRepository.save(giftBill);
        memberShipCardRepository.save(giftBill.getMembershipCard());

        return "fragments/exchange-gift-done";
    }
}
