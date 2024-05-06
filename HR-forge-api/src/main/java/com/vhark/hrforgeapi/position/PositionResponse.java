package com.vhark.hrforgeapi.position;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PositionResponse {

  private Long positionId;
  private String name;
  private String description;
}
