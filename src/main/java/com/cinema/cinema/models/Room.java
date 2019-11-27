package com.cinema.cinema.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    public Room() {
    }

    public Room(List<Showtime> showtimes, int roomNumber) {
        this.showtimes = showtimes;
        this.roomNumber = roomNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Showtime> showtimes;

    @Column(name = "room_number")
    private int roomNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
