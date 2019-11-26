package com.cinema.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "manages")
public class Manage extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


}
