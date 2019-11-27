package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Film;
import com.cinema.cinema.models.Room;
import com.cinema.cinema.models.Showtime;
import com.cinema.cinema.models.TimePrice;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<TimePrice> timePrices = (List<TimePrice>) timePriceRepository.findAll();
        model.addAttribute("film", films);
        model.addAttribute("timePrice", timePrices);
        return "fragments/show-time-create";
    }

    @PostMapping("/show-times/store")
    public String store(@RequestParam(name = "film_id", required = false, defaultValue = "0") String filmid,
                        @RequestParam(name = "time_price_id", required = false, defaultValue = "0") String timepriceid,
                        @RequestParam(name = "room_id", required = false, defaultValue = "0") String roomid, Model model) {
        int film_id = Integer.parseInt(filmid);
        int time_price_id = Integer.parseInt(timepriceid);
        int room_id = Integer.parseInt(roomid);
        if (film_id == 0 || time_price_id == 0 || room_id == 0) {
            model.addAttribute("error", "chon cac gia tri");
            return "fragments/show-time-create";
        }
        Film film = filmRepository.findById(film_id).get();
        Room room = roomRepository.findById(room_id).get();
        TimePrice timePrice = timePriceRepository.findById(time_price_id).get();
        showTimeRepository.save(new Showtime(room, film, timePrice));
        return "redirect:/show-times";
    }
}
