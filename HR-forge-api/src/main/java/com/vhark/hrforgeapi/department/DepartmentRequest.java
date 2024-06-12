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

  @NotEmpty(message = "Department name is mandatory")
  @NotBlank(message = "Department name is mandatory")
  private String name;
  private String description;
}
