package com.vhark.hrforgeapi.personal;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordRequest {

  @Size(min = 6, message = "New password should contain at least 6 characters")
  @NotEmpty(message = "New password is mandatory")
  @NotBlank(message = "New password is mandatory")
  private String newPassword;

  @Size(min = 6, message = "Old password should contain at least 6 characters")
  @NotEmpty(message = "Old password is mandatory")
  @NotBlank(message = "Old password is mandatory")
  private String oldPassword;
}
