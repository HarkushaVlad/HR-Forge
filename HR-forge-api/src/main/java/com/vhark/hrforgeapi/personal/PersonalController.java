package com.vhark.hrforgeapi.personal;

import com.vhark.hrforgeapi.employee.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("personal")
@RequiredArgsConstructor
@Tag(name = "Personal", description = "API for managing personal information")
public class PersonalController {

  private final PersonalService personalService;

  @Operation(
      summary = "Find personal information",
      description = "Endpoint for retrieving personal information of the authenticated user.")
  @GetMapping()
  public ResponseEntity<EmployeeResponse> find(Authentication authentication) {
    return ResponseEntity.ok(personalService.find(authentication));
  }

  @Operation(
      summary = "Update personal information",
      description = "Endpoint for updating personal information of the authenticated user.",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Updated personal information",
              required = true,
              content = @Content(schema = @Schema(implementation = PersonalRequest.class))))
  @PutMapping()
  public ResponseEntity<?> update(
      @RequestBody @Valid PersonalRequest request, Authentication authentication) {
    personalService.update(authentication, request);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Update password",
      description = "Endpoint for updating password of the authenticated user.",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "New password",
              required = true,
              content = @Content(schema = @Schema(implementation = PasswordRequest.class))))
  @PutMapping("/password")
  public ResponseEntity<?> updatePassword(
      @RequestBody @Valid PasswordRequest request, Authentication authentication) {
    personalService.updatePassword(authentication, request);
    return ResponseEntity.noContent().build();
  }
}
