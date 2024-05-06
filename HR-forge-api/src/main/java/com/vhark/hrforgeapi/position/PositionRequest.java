package com.vhark.hrforgeapi.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PositionRequest {

  private String name;
  private String description;
}
