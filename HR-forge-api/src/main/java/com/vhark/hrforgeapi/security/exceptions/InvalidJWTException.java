package com.vhark.hrforgeapi.security.exceptions;

public class InvalidJWTException extends RuntimeException {

  public InvalidJWTException() {
    super("Invalid JWT");
  }
}
