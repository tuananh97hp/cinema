package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Film;
import com.cinema.cinema.models.Showtime;
import com.cinema.cinema.models.TimePrice;
import com.cinema.cinema.repositories.FilmRepository;
import com.cinema.cinema.repositories.RoomRepository;
import com.cinema.cinema.repositories.ShowTimeRepository;
import com.cinema.cinema.repositories.TimePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TimePriceController {

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TimePriceRepository timePriceRepository;

    @GetMapping("/time-prices")
    public ResponseEntity<?> getByRoom(@RequestParam(name = "room_id", required = false, defaultValue = "0") int roomId, @RequestParam(name = "date", required = false, defaultValue = "0") String date, @RequestParam(name = "film_id", required = false, defaultValue = "0") int filmId, Model model) {
        List<TimePrice> timePricesResponse = new ArrayList<>();
        if (roomId != 0 && filmId != 0 && !date.equals("0")) {
            List<TimePrice> timePrices = (List<TimePrice>) timePriceRepository.findAll();
            List<Showtime> showTimes = showTimeRepository.findAllByRoomIdAndDate(roomId, date);
            Film film = filmRepository.findById(filmId).get();
            timePricesResponse.addAll(timePrices);

            for (Showtime showtime: showTimes) {
                LocalTime localTimeMax = showtime.getTimePrice().getTime().plusMinutes(showtime.getFilm().getTime());
                LocalTime localTimeMin = showtime.getTimePrice().getTime().plusMinutes(-film.getTime());

                for (TimePrice timePrice : timePrices) {
                    if (((localTimeMin.compareTo(timePrice.getTime()) == -1 || localTimeMin.compareTo(timePrice.getTime()) == 0)
                            && (localTimeMax.compareTo(timePrice.getTime()) == 1 || localTimeMax.compareTo(timePrice.getTime()) == 0))
                            ||(LocalTime.now().compareTo(timePrice.getTime()) == 1 && date.compareTo(String.valueOf(LocalDate.now())) == 0)) {
                        timePricesResponse.remove(timePrice);
                    }
                }
            }
        }
        return ResponseEntity.ok(timePricesResponse);
    }

}
