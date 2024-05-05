package com.vhark.hrforgeapi.position.exceptions;

public class PositionNameIsAlreadyInUseException extends RuntimeException {

  public PositionNameIsAlreadyInUseException(String positionName) {
    super("Position name " + positionName + " is already in use");
  }
}
