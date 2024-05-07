package com.vhark.hrforgeapi.personal;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordRequest {

  @Size(min = 6, message = "Password should contain at least 6 characters")
  @NotEmpty(message = "Password is mandatory")
  @NotBlank(message = "Password is mandatory")
  private String password;
}
