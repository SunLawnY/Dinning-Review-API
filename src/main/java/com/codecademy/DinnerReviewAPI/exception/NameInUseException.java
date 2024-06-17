package com.codecademy.DinnerReviewAPI.exception;

public class NameInUseException extends Exception{
    public NameInUseException(String message){
        super(message);
    }
}
