package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Film;
import com.cinema.cinema.models.Room;
import com.cinema.cinema.models.Showtime;
import com.cinema.cinema.repositories.FilmRepository;
import com.cinema.cinema.repositories.RoomRepository;
import com.cinema.cinema.repositories.ShowTimeRepository;
import com.cinema.cinema.repositories.TimePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShowtimesController {

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
        List<Room> rooms = (List<Room>) roomRepository.findAll();
        model.addAttribute("films", films);
        model.addAttribute("rooms", rooms);
        model.addAttribute("showTime", new Showtime());
        return "fragments/show-time-create";
    }

    @PostMapping("/show-times/create")
    public String store(@Valid Showtime showtime, BindingResult result,  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("showTime", showtime);
            return "fragments/show-time-create";
        }
        showTimeRepository.save(showtime);
        return "redirect:/show-times";
    }

}
