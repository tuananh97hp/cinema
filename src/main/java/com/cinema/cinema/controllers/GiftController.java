package com.cinema.cinema.controllers;

import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Validated
public class GiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @GetMapping("/exchange-gift")
    public String showExchangeGiftView(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId, Model model) {
        int parseCardId = 0;
        try {
            parseCardId = Integer.parseInt(cardId);
        } catch (NumberFormatException e) {

        }

        Optional<MembershipCard> optionalMembershipCard = memberShipCardRepository.findById(parseCardId);
        MembershipCard membershipCard = null;
        if (optionalMembershipCard.isPresent()) {
            membershipCard = optionalMembershipCard.get();
        }
        model.addAttribute("memberCard", membershipCard);
        model.addAttribute("cardId", parseCardId == 0 ? "" : cardId);
        return "fragments/exchange-gifts";
    }

}
