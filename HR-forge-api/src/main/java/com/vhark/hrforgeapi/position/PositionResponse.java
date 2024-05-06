package com.vhark.hrforgeapi.position;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PositionResponse {

  private Long positionId;
  private String name;
  private String description;
}
