package com.vhark.hrforgeapi.auth;

import jakarta.validation.constraints.*;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

  @NotEmpty(message = "Department is mandatory")
  @NotBlank(message = "Department is mandatory")
  private String departmentName;

  @NotEmpty(message = "Position is mandatory")
  @NotBlank(message = "Position is mandatory")
  private String positionName;

  @NotEmpty(message = "Firstname is mandatory")
  @NotBlank(message = "Firstname is mandatory")
  private String firstName;

  @NotEmpty(message = "Lastname is mandatory")
  @NotBlank(message = "Lastname is mandatory")
  private String lastName;

  @NotNull(message = "Birth date is mandatory")
  private Date birthDate;

  @Email(message = "Email is not formatted")
  @NotEmpty(message = "Email is mandatory")
  @NotBlank(message = "Email is mandatory")
  private String email;

  @Size(min = 6, message = "Password should contain at least 6 characters")
  @NotEmpty(message = "Password is mandatory")
  @NotBlank(message = "Password is mandatory")
  private String password;

  @NotNull(message = "Salary is mandatory")
  private Double salary;

  @NotNull(message = "Hire date is mandatory")
  private Date hireDate;
}
