package com.cinema.cinema.models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "time_prices")
public class TimePrice {
    public TimePrice() {
    }

    public TimePrice(LocalTime time, float price) {
        this.time = time;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timePrice")
    private List<Showtime> showtimes;

    @Column(name = "time", columnDefinition = "TIME")
    private LocalTime time;

    @Column(name = "price", columnDefinition = "double default 0")
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
