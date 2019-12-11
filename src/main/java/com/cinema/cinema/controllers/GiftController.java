package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Gift;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.repositories.GiftRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Validated
@RequestMapping("/exchange-gift")
public class GiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftRepository giftRepository;

    @ModelAttribute
    public MembershipCard getMembershipCard(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId) {
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

        return membershipCard;
    }

    @GetMapping("")
    public String showExchangeGiftView(@ModelAttribute("membershipCard") MembershipCard membershipCard, Model model) {
        model.addAttribute("memberCard", membershipCard);

        return "fragments/exchange-gifts";
    }

    @GetMapping("/{cartId}")
    public String showSearchGiftView(@PathVariable("cartId") String cardId,
                                     @RequestParam(name = "gift_search", required = false, defaultValue = "") String giftSearch,
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

        List<Gift> gifts = giftRepository.findGiftByFoodNameContainingAndPointIsLessThanOrTicketShowtimeFilmNameContainingAndPointIsLessThanOrderByPoint(
                giftSearch,
                membershipCard != null ? membershipCard.getPoint() : 0,
                giftSearch,
                membershipCard != null ? membershipCard.getPoint() : 0);
        model.addAttribute("gifts", gifts);
        model.addAttribute("giftSearch", giftSearch);
        model.addAttribute("memberCard", membershipCard);

        return "fragments/list-gifts";
    }

    @GetMapping("/{cartId}/{giftId}")
    public String showGiftBill(@PathVariable("cartId") String cardId,
                                    @PathVariable("giftId") String giftId,
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

        model.addAttribute("gift", gift);
        model.addAttribute("memberCard", membershipCard);

        return "fragments/gift-bill";
    }

}
