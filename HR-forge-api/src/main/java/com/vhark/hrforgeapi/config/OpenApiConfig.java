package com.vhark.hrforgeapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            contact = @Contact(name = "Vhark", email = "contact@vhark.com"),
            description = "OpenApi documentation for Hr-forge-api",
            title = "OpenApi specification - Vhark",
            version = "1.0",
            license = @License(name = "No license"),
            termsOfService = "Terms of service"),
    servers = {
      @Server(description = "Local ENV", url = "http://localhost:8080/api/v1"),
      @Server(description = "PROD ENV", url = "https://some-url.com")
    },
    security = {@SecurityRequirement(name = "bearerAuth")})
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {}
