package com.cinema.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GiftController {

    @GetMapping("/exchange-gift")
    public String showExchangeGiftView(@RequestParam(name = "card_id", required = false, defaultValue = "0") String cardId, Model model) {

        return "fragments/exchange-gifts";
    }

}
