package com.vhark.hrforgeapi.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminPasswordRequest {

  @Size(min = 6, message = "New password should contain at least 6 characters")
  @NotEmpty(message = "New password is mandatory")
  @NotBlank(message = "New password is mandatory")
  private String newPassword;
}
