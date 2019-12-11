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

    @ModelAttribute(name = "membershipCardFromParam")
    public MembershipCard getMembershipCard(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId) {
        return findMembershipCardByStringId(cardId);
    }

    @ModelAttribute(name = "membershipCardFromPath")
    public MembershipCard getMembershipCardFromPath(@PathVariable(name = "cartId", required = false) String cardId) {
        return findMembershipCardByStringId(cardId);
    }

    @ModelAttribute(name = "giftsFromParam")
    public List<Gift> getGiftsFromParam(@ModelAttribute(name = "membershipCardFromPath") MembershipCard membershipCard,
                                       @RequestParam(name = "gift_search", required = false, defaultValue = "") String giftSearch) {
        return giftRepository.findGiftByFoodNameContainingAndPointIsLessThanOrTicketShowtimeFilmNameContainingAndPointIsLessThanOrderByPoint(
                giftSearch,
                membershipCard != null ? membershipCard.getPoint() : 0,
                giftSearch,
                membershipCard != null ? membershipCard.getPoint() : 0);
    }

    @ModelAttribute(name = "giftFromPath")
    public Gift getGiftFromPath(@PathVariable(name = "giftId", required = false) String giftId) {
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

        return gift;
    }

    @GetMapping("")
    public String showExchangeGiftView(@ModelAttribute(name = "membershipCardFromParam") MembershipCard membershipCard, Model model) {
        model.addAttribute("memberCard", membershipCard);

        return "fragments/exchange-gifts";
    }

    @GetMapping("/{cartId}")
    public String showSearchGiftView(@ModelAttribute(name = "membershipCardFromPath") MembershipCard membershipCard,
                                     @ModelAttribute(name = "giftsFromParam") List<Gift> gifts,
                                     Model model) {
        model.addAttribute("gifts", gifts);
        model.addAttribute("memberCard", membershipCard);

        return "fragments/list-gifts";
    }

    @GetMapping("/{cartId}/{giftId}")
    public String showGiftBill(@ModelAttribute(name = "membershipCardFromPath") MembershipCard membershipCard,
                               @ModelAttribute(name = "giftFromPath") Gift gift,
                               Model model) {
        model.addAttribute("gift", gift);
        model.addAttribute("memberCard", membershipCard);

        return "fragments/gift-bill";
    }

    private MembershipCard findMembershipCardByStringId(String id) {
        int parseCardId = 0;
        try {
            parseCardId = Integer.parseInt(id);
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
}
