package com.cinema.cinema.models;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "films")
public class Film {
    public Film() {
    }

    public Film(@NotNull String name, @NotEmpty int time) {
        this.name = name;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "time")
    @NotEmpty
    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
