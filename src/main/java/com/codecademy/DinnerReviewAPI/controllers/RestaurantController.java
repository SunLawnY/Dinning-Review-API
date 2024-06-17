package com.codecademy.DinnerReviewAPI.controllers;

import com.codecademy.DinnerReviewAPI.entities.Restaurant;
import com.codecademy.DinnerReviewAPI.repositories.RestaurantRepository;
import com.codecademy.DinnerReviewAPI.exception.RestaurantExisitException;
import com.codecademy.DinnerReviewAPI.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/")
    public Iterable<Restaurant> listAllRestaurant(){
        return this.restaurantRepository.findAll();
    }

    @PostMapping("/submitNewRestaurant")
    public ResponseEntity<?> createNewRestaurant (@RequestBody Restaurant restaurant) throws RestaurantExisitException {
        Optional<Restaurant> checkRestaurant = this.restaurantRepository.findByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if (checkRestaurant.isEmpty()){
            Restaurant newRestaurant = checkRestaurant.get();
            return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
        } else {
            throw new RestaurantExisitException("Restaurant has been registered");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById (@PathVariable("id") Long id) throws RestaurantNotFoundException {
        Optional<Restaurant> checkRestaurant = this.restaurantRepository.findById(id);
        if (checkRestaurant.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant not found");
        } else {
            Restaurant result = checkRestaurant.get();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    /*
    Restaurant entity-related scenarios:

    As an application experience, I want to submit a new restaurant entry. Should a restaurant with the same name and with the same zip code already exist, I will see a failure.
    As an application experience, I want to fetch the details of a restaurant, given its unique Id.
    As an application experience, I want to fetch restaurants that match a given zip code and that also have at least one user-submitted score for a given allergy. I want to see them sorted in descending order.
     */
}
