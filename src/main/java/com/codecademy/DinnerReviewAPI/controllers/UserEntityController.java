package com.codecademy.DinnerReviewAPI.controllers;

import com.codecademy.DinnerReviewAPI.entities.UserEntity;
import com.codecademy.DinnerReviewAPI.exception.NameInUseException;
import com.codecademy.DinnerReviewAPI.exception.UserNotFound;
import com.codecademy.DinnerReviewAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserEntityController {
    private final UserRepository userRepository;

    @Autowired
    public UserEntityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public Iterable<UserEntity> getAllUser (){
        return this.userRepository.findAll();
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createNewUser (@RequestBody UserEntity newUser) throws NameInUseException {
        try {
            Optional<UserEntity> userNameCheck = this.userRepository.findByName(newUser.getName());
            if (userNameCheck.isEmpty()) {
                this.userRepository.save(newUser);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            } else {
                throw new NameInUseException("Name already been used");
            }
        } catch (NameInUseException e) {
            return new ResponseEntity<>("Name in used!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile (@RequestBody UserEntity updateUser){
        Optional<UserEntity> userEntityOptional = this.userRepository.findByName(updateUser.getName());
        try {
            if (userEntityOptional.isEmpty()) {
                throw new UserNotFound("Invalid ID");
            } else {
                UserEntity userToReplace = userEntityOptional.get();
                if (updateUser.getCity() != null) {
                    userToReplace.setCity(updateUser.getCity());
                }
                if (updateUser.getState() != null) {
                    userToReplace.setState(updateUser.getState());
                }
                if (updateUser.getZipcode() != null) {
                    userToReplace.setZipcode(updateUser.getZipcode());
                }
                if (updateUser.getAllergiesPeanut() != null) {
                    userToReplace.setAllergiesPeanut(updateUser.getAllergiesPeanut());
                }
                if (updateUser.getAllergiesEgg() != null) {
                    userToReplace.setAllergiesEgg(updateUser.getAllergiesEgg());
                }
                if (updateUser.getAllergiesdairy() != null) {
                    userToReplace.setAllergiesdairy(updateUser.getAllergiesdairy());
                }
                UserEntity UpdatedUser = this.userRepository.save(userToReplace);
                return new ResponseEntity<>(UpdatedUser, HttpStatus.OK);
            }
        } catch (UserNotFound e){
            return new ResponseEntity<>("Name not found", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserInfo (@PathVariable("userName") String name) throws UserNotFound {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByName(name);
        try {
            if (userEntityOptional.isEmpty()) {
                throw new UserNotFound("Invalid Name input");
            } else {
                UserEntity foundUser = userEntityOptional.get();
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            }
        } catch (UserNotFound e){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    /*
        As an unregistered user, I want to create my user profile using a display name that’s unique only to me.
        As a registered user, I want to update my user profile. I cannot modify my unique display name.
        As an application experience, I want to fetch the user profile belonging to a given display name.
        As part of the backend process that validates a user-submitted dining review, I want to verify that the user exists, based on the user display name associated with the dining review.
     */
}
