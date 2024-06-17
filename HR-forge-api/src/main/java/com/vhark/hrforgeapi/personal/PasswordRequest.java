package com.vhark.hrforgeapi.personal;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordRequest {

  @Size(min = 6, message = "Новий пароль повинен містити щонайменше 6 символів")
  @NotEmpty(message = "Новий пароль є обов'язковим")
  @NotBlank(message = "Новий пароль є обов'язковим")
  private String newPassword;

  @Size(min = 6, message = "Старий пароль повинен містити щонайменше 6 символів")
  @NotEmpty(message = "Старий пароль є обов'язковим")
  @NotBlank(message = "Старий пароль є обов'язковим")
  private String oldPassword;
}
