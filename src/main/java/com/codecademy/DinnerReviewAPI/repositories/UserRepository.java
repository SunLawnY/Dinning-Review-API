package com.codecademy.DinnerReviewAPI.repositories;

import com.codecademy.DinnerReviewAPI.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
