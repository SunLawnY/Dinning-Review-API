package com.codecademy.DinnerReviewAPI.repositories;

import com.codecademy.DinnerReviewAPI.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
}
