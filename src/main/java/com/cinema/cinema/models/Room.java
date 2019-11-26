package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


}
