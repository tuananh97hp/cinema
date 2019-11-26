package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    public Ticket(Showtime showtime, Chair chair) {
        this.showtime = showtime;
        this.chair = chair;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "show_time_id", referencedColumnName = "id")
    private Showtime showtime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chair_id", referencedColumnName = "id")
    private Chair chair;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }
}
