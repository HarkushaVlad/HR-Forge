package com.vhark.hrforgeapi.position.exceptions;

public class InvalidPositionIdException extends RuntimeException {

  public InvalidPositionIdException(String invalidId) {
    super(
        "Invalid position ID: "
            + invalidId
            + ", please insert a valid position ID or a position name");
  }
}
