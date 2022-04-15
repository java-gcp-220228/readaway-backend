package com.revature.readawaybackend.exceptions;

public class UserExistsException extends Exception {
  public UserExistsException(String message) {
    super(message);
  }
}