package com.vhark.hrforgeapi.personal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalRequest {

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
}
