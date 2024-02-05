package com.rest.swagger_assignment.exception;

//UserNotFoundException.java (Custom Exception)
public class UserNotFoundException extends RuntimeException {
 public UserNotFoundException(String message) {
     super(message);
 }
}

