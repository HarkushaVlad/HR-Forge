package com.vhark.hrforgeapi.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentRequest {

  @NotEmpty(message = "Назва відділу є обов'язковою")
  @NotBlank(message = "Назва відділу є обов'язковою")
  private String name;

  private String description;
}
