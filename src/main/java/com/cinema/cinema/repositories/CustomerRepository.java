package com.cinema.cinema.repositories;

import com.cinema.cinema.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
