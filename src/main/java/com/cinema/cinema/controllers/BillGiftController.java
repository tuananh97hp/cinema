package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Gift;
import com.cinema.cinema.models.GiftBill;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.repositories.GiftBillRepository;
import com.cinema.cinema.repositories.GiftRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@Validated
@RequestMapping("/bill-gift")
public class BillGiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private GiftBillRepository giftBillRepository;

    @PostMapping("")
    public String addBillGift(
            @RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId,
            @RequestParam(name = "gift_id", required = false, defaultValue = "0") String giftId,
            Model model) {
        int parseCardId = 0;
        try {
            parseCardId = Integer.parseInt(cardId);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }

        Optional<MembershipCard> optionalMembershipCard = memberShipCardRepository.findById(parseCardId);
        MembershipCard membershipCard = null;
        if (optionalMembershipCard.isPresent()) {
            membershipCard = optionalMembershipCard.get();
        }

        int parseGiftId = 0;
        try {
            parseGiftId = Integer.parseInt(giftId);
        } catch (NumberFormatException e) {
            System.out.println(e.toString());
        }

        Optional<Gift> optionalGift = giftRepository.findById(parseGiftId);
        Gift gift = null;
        if (optionalGift.isPresent()) {
            gift = optionalGift.get();
        }

        if (membershipCard != null && gift != null) {
            membershipCard.setPoint(membershipCard.getPoint() - gift.getPoint());
            memberShipCardRepository.save(membershipCard);

            GiftBill giftBill = new GiftBill(gift, membershipCard);
            giftBillRepository.save(giftBill);
        }

        return "fragments/exchange-gift-done";
    }
}
