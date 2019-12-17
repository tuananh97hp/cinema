package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Gift;
import com.cinema.cinema.models.GiftBill;
import com.cinema.cinema.models.MembershipCard;
import com.cinema.cinema.models.OrderGift;
import com.cinema.cinema.repositories.GiftRepository;
import com.cinema.cinema.repositories.MemberShipCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Validated
@RequestMapping("/exchange-gift")
public class ExchangeGiftController {

    @Autowired
    private MemberShipCardRepository memberShipCardRepository;

    @Autowired
    private GiftRepository giftRepository;

    @ModelAttribute(name = "membershipCardFromParam")
    public MembershipCard getMembershipCard(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId) {
        return memberShipCardRepository.findByStringId(cardId);
    }

    @ModelAttribute(name = "membershipCardFromPath")
    public MembershipCard getMembershipCardFromPath(@PathVariable(name = "card_id", required = false) String cardId) {
        return memberShipCardRepository.findByStringId(cardId);
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
    public Gift getGiftFromPath(@PathVariable(name = "gift_id", required = false) String giftId) {
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

    @GetMapping("/{card_id}")
    public String showSearchGiftView(@ModelAttribute(name = "membershipCardFromPath") MembershipCard membershipCard,
                                     @ModelAttribute(name = "giftsFromParam") List<Gift> gifts,
                                     Model model) {
        OrderGift orderGift = new OrderGift();
        orderGift.setQuantity(1);
        model.addAttribute("orderGift", orderGift);
        model.addAttribute("gifts", gifts);
        model.addAttribute("memberCard", membershipCard);

        return "fragments/list-gifts";
    }

    @GetMapping("/{card_id}/{gift_id}")
    public String showGiftBill(@ModelAttribute(name = "membershipCardFromPath") MembershipCard membershipCard,
                               @ModelAttribute(name = "giftFromPath") Gift gift,
                               OrderGift orderGift,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession,
                               HttpServletRequest httpServletRequest,
                               Model model) {
        if ((orderGift.getQuantity() * gift.getPoint()) > membershipCard.getPoint()) {
            redirectAttributes.addFlashAttribute("not_enough_point", "Thẻ không đủ điểm");
            String referer = httpServletRequest.getHeader("Referer");
            return "redirect:"+ referer;
        }

        orderGift.setGift(gift);
        orderGift.setPoint(orderGift.getQuantity() * gift.getPoint());

        Set<OrderGift> setOrderGifts = new HashSet<>();
        setOrderGifts.add(orderGift);
        GiftBill giftBill = new GiftBill(setOrderGifts, membershipCard);

        orderGift.setGiftBill(giftBill);

        httpSession.setAttribute("giftBill", giftBill);

        model.addAttribute("giftBill", giftBill);

        return "fragments/gift-bill";
    }

}
