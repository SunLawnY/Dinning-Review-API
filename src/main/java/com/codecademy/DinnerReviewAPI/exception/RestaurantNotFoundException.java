package com.codecademy.DinnerReviewAPI.exception;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
