package com.vhark.hrforgeapi.position.exceptions;

public class PositionNotFoundException extends RuntimeException {

  public PositionNotFoundException(Long id) {
    super("Position with id " + id + " not found");
  }

  public PositionNotFoundException(String name) {
    super("Position with name " + name + " not found");
  }
}
