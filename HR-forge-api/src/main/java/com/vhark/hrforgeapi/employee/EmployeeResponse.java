package com.vhark.hrforgeapi.employee;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

  private Long employeeId;
  private String departmentName;
  private String positionName;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String email;
  private Double salary;
  private Date hireDate;
}
