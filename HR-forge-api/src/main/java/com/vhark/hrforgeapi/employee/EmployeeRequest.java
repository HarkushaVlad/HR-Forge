package com.vhark.hrforgeapi.employee;

import jakarta.validation.constraints.*;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeRequest {

  @NotEmpty(message = "Відділ є обов'язковим")
  @NotBlank(message = "Відділ є обов'язковим")
  private String departmentName;

  @NotEmpty(message = "Посада є обов'язковою")
  @NotBlank(message = "Посада є обов'язковою")
  private String positionName;

  @NotEmpty(message = "Ім'я є обов'язковим")
  @NotBlank(message = "Ім'я є обов'язковим")
  private String firstName;

  @NotEmpty(message = "Прізвище є обов'язковим")
  @NotBlank(message = "Прізвище є обов'язковим")
  private String lastName;

  @NotNull(message = "Дата народження є обов'язковою")
  private Date birthDate;

  @Email(message = "Електронна пошта не відповідає формату")
  @NotEmpty(message = "Електронна пошта є обов'язковою")
  @NotBlank(message = "Електронна пошта є обов'язковою")
  private String email;

  @NotNull(message = "Зарплата є обов'язковою")
  private Double salary;

  @NotNull(message = "Дата прийняття на роботу є обов'язковою")
  private Date hireDate;
}
