package com.cinema.cinema.models;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "time_prices")
public class TimePrice {

    public TimePrice(String time, float price) {
        this.time = time;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private String time;

    @Column(name = "price", columnDefinition = "double default 0")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
