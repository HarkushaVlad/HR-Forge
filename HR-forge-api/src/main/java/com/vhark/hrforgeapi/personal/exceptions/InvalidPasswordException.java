package com.vhark.hrforgeapi.personal.exceptions;

public class InvalidPasswordException extends RuntimeException {

  public InvalidPasswordException() {
    super("Current password is incorrect");
  }
}
