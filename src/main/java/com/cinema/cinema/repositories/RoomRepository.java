package com.cinema.cinema.repositories;

import com.cinema.cinema.models.Room;
import com.cinema.cinema.models.Showtime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Integer>{
    List<Room> findAllByShowtimesIn(List<Showtime> showtimes);

    List<Room> findRoomByIdNotIn(List<Integer> ids);
}
