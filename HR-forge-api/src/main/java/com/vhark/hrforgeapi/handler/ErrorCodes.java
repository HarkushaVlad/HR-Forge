package com.vhark.hrforgeapi.handler;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
  NO_CODE(0, NOT_IMPLEMENTED, "No code"),
  INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is incorrect"),
  NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "The new password does not match"),
  BAD_CREDENTIALS(303, FORBIDDEN, "Login and / or password is incorrect");

  private final int code;
  private final String description;
  private final HttpStatus httpStatus;

  ErrorCodes(int code, HttpStatus httpStatus, String description) {
    this.code = code;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
