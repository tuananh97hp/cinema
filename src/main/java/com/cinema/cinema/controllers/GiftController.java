package com.cinema.cinema.controllers;

import com.cinema.cinema.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GiftController {

    @Autowired
    private GiftRepository giftRepository;

    @GetMapping("/exchange-gift")
    public String showExchangeGiftView(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId, Model model) {
        System.out.println(giftRepository.findAll());
        return "fragments/exchange-gifts";
    }

}
