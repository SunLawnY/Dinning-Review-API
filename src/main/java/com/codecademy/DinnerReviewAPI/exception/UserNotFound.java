package com.codecademy.DinnerReviewAPI.exception;

public class UserNotFound extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}
