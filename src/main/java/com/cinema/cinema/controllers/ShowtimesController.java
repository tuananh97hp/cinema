package com.cinema.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowtimesController {
    @GetMapping("/showtimes")
    public String index(Model model) {
        return "fragments/showtime-list";
    }
}
