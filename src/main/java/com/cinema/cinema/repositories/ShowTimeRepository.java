package com.cinema.cinema.repositories;

import com.cinema.cinema.models.Showtime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowTimeRepository extends CrudRepository<Showtime, Integer> {
    List<Showtime> findAllByRoomIdAndDate(Integer id, String date);
}
