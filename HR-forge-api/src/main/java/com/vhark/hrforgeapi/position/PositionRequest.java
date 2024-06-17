package com.vhark.hrforgeapi.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PositionRequest {

  @NotEmpty(message = "Назва посади є обов'язковою")
  @NotBlank(message = "Назва посади є обов'язковою")
  private String name;

  private String description;
}
