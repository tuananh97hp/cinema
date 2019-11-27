package com.cinema.cinema.controllers;

import com.cinema.cinema.models.Room;
import com.cinema.cinema.models.Showtime;
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

import java.util.LinkedList;
import java.util.List;

@Controller
public class RoomController {


    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TimePriceRepository timePriceRepository;

    @GetMapping("/rooms")
    public ResponseEntity<?> index(@RequestParam(name = "time_price_id", required = false, defaultValue = "0") int timePriceId, Model model) {
        List<Showtime> showtimes = showTimeRepository.findAllByTimePriceId(timePriceId);
        List<Room> roomListIn = roomRepository.findAllByShowtimesIn(showtimes);
        List<Integer> room_ids = new LinkedList<>();
        for (Room room : roomListIn) {
            room_ids.add(room.getId());
        }
        List<Room> roomList;
        if (room_ids.size() == 0) {
            roomList = (List<Room>) roomRepository.findAll();
        } else {
            roomList = roomRepository.findRoomByIdNotIn(room_ids);
        }

        return ResponseEntity.ok(roomList);
    }
}
