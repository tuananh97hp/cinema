package com.cinema.cinema.repositories;

import com.cinema.cinema.models.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Integer> {

    List<Gift> findGiftByFoodNameContainingAndPointIsLessThanOrTicketShowtimeFilmNameContainingAndPointIsLessThanOrderByPoint(String foodName, int cardPoint1, String filmName, int cardPoint2);
}
