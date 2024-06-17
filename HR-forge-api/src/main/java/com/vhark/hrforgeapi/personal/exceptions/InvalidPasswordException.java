package com.vhark.hrforgeapi.personal.exceptions;

public class InvalidPasswordException extends RuntimeException {

  public InvalidPasswordException() {
    super("Поточний пароль невірний");
  }
}
