package com.vhark.hrforgeapi.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API for user authentication and registration")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @Operation(
          summary = "Register a new user",
          description = "Endpoint for registering a new user in the system.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Registration request details",
                  required = true,
                  content = @Content(schema = @Schema(implementation = RegistrationRequest.class)))
  )
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
    authenticationService.register(request);
    return ResponseEntity.accepted().build();
  }

  @Operation(
          summary = "Authenticate a user",
          description = "Endpoint for authenticating a user and generating an authentication token.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "Authentication request details",
                  required = true,
                  content = @Content(schema = @Schema(implementation = AuthenticationRequest.class)))
  )
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody @Valid AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
