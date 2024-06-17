package com.codecademy.DinnerReviewAPI.repositories;

import com.codecademy.DinnerReviewAPI.entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
