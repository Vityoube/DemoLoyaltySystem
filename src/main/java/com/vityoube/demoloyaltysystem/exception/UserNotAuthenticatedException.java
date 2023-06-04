package com.vityoube.demoloyaltysystem.exception;

public class UserNotAuthenticatedException extends Exception{

    @Override
    public String getMessage() {
        return "User is not authenticated";
    }
}
