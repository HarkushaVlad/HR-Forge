package com.vhark.hrforgeapi.department;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentResponse {

  private Long departmentId;
  private String name;
  private String description;
}
