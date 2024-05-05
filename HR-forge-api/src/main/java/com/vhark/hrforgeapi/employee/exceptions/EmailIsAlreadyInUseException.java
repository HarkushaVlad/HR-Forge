package com.vhark.hrforgeapi.employee.exceptions;

public class EmailIsAlreadyInUseException extends RuntimeException {

  public EmailIsAlreadyInUseException(String email) {
    super("Email is already in use: " + email);
  }
}
