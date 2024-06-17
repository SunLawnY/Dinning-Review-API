package com.codecademy.DinnerReviewAPI.controllers;

import com.codecademy.DinnerReviewAPI.entities.Review;
import com.codecademy.DinnerReviewAPI.entities.UserEntity;
import com.codecademy.DinnerReviewAPI.exception.NameInUseException;
import com.codecademy.DinnerReviewAPI.exception.UserNotFound;
import com.codecademy.DinnerReviewAPI.repositories.RestaurantRepository;
import com.codecademy.DinnerReviewAPI.repositories.ReviewRepository;
import com.codecademy.DinnerReviewAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class DinnerReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public DinnerReviewController(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public Iterable<Review> getAlReview (){
        return this.reviewRepository.findAll();
    }

    @PostMapping("{userName}/submitReview")
    public ResponseEntity<?> createNewUser (@PathVariable("userName") String name, @RequestBody Review review) {
        try {
            Optional<UserEntity> userNameCheck = this.userRepository.findByName(name);
            if (userNameCheck.isEmpty()) {
                throw new UserNotFound("User not found");
            } else {
                Review submitReview = this.reviewRepository.save(review);
                return new ResponseEntity<>(review, HttpStatus.CREATED);
            }
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
    }


    /*
    @PutMapping("/{id}/quantity/decrement")
	public Boot decrementQuantity(@PathVariable("id") Integer id) {
      Optional<Boot> bootDecreaseOptional = this.bootRepository.findById(id);

    Dining review entity-related scenarios:
        As a registered user, I want to submit a dining review.
        As an admin, I want to get the list of all dining reviews that are pending approval.
        As an admin, I want to approve or reject a given dining review.
        As part of the backend process that updates a restaurant’s set of scores, I want to fetch the set of all approved dining reviews belonging to this restaurant.
    */
}