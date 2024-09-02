package com.example.bookhub.config;

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
        info = @Info(
                contact = @Contact(
                        name = "Ariane",
                        email = "desvalsariane@gmail.com",
                        url = "https://www.linkedin.com/in/arianebc/"
                ),
                description = "OpenApi documentation for Spring security",
                title = "OpenApi specification - Ariane's API",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                termsOfService = "https://www.example.com/terms-of-service"
        ),
        servers = {
                @Server(
                        description = "Local Development Environment",
                        url = "http://localhost:8088/api/v1"
                ),
                @Server(
                        description = "Production Environment",
                        url = "https://www.example.com/bookhub"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication scheme used for securing the API endpoints. Provide the JWT token in the Authorization header as 'Bearer {token}'.",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
