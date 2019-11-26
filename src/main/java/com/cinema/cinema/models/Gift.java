package com.cinema.cinema.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "gifts")
public class Gift {

    public Gift(Ticket ticket, Food food, int point) {
        this.ticket = ticket;
        this.food = food;
        this.point = point;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    @Column(name = "point", columnDefinition = "integer default 0")
    private int point;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
