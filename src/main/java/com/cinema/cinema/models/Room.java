package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "rooms")
public class Room {
    public Room() {
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
