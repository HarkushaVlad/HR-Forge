package com.vhark.hrforgeapi.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

  @Email(message = "Електронна пошта не відповідає формату")
  @NotEmpty(message = "Електронна пошта є обов'язковою")
  @NotBlank(message = "Електронна пошта є обов'язковою")
  private String email;

  @Size(min = 6, message = "Пароль повинен містити щонайменше 6 символів")
  @NotEmpty(message = "Пароль є обов'язковим")
  @NotBlank(message = "Пароль є обов'язковим")
  private String password;
}
