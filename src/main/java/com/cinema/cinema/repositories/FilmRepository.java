package com.cinema.cinema.repositories;

import com.cinema.cinema.models.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
