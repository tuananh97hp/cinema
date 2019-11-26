package com.cinema.cinema.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "show_times")
public class Showtime {

    public Showtime(){}

    public Showtime(Room room, Film film, TimePrice timePrice) {
        this.room = room;
        this.film = film;
        this.timePrice = timePrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_price_id", referencedColumnName = "id")
    private TimePrice timePrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public TimePrice getTimePrice() {
        return timePrice;
    }

    public void setTimePrice(TimePrice timePrice) {
        this.timePrice = timePrice;
    }
}
