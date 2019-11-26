package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Film;
import com.cinema.cinema.models.Showtime;
import com.cinema.cinema.models.TimePrice;
import com.cinema.cinema.repositories.FilmRepository;
import com.cinema.cinema.repositories.RoomRepository;
import com.cinema.cinema.repositories.ShowTimeRepository;
import com.cinema.cinema.repositories.TimePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShowtimesController{

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TimePriceRepository timePriceRepository;

    @GetMapping("/show-times")
    public String index(Model model) {
        List<Showtime> showTimes = (List<Showtime>) showTimeRepository.findAll();
        model.addAttribute("showTimes", showTimes);
        return "fragments/show-time-list";
    }

    @GetMapping("/show-times/create")
    public String create(Model model) {
        List<Film> films = (List<Film>) filmRepository.findAll();
        List<TimePrice> timePrices = (List<TimePrice>) timePriceRepository.findAll();
        model.addAttribute("film", films);
        model.addAttribute("timePrice", timePrices);
        return "fragments/show-time-create";
    }


}
