package fr.config;

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
            name = "Charly",
            email = "Mettre Email conforme"
        ),
        description = "",
        title = "API for Prestapoint",
        version = "1.0",
        license = @License(
                name = "License name", 
                url = "some-url"
        ),
        termsOfService = "Terms of service"
    ), 
    servers = {
        @Server(
            description = "Local ENV",
            url = "http://localhost:8080/"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
@SecurityRequirement(name = "bearerAuth") 
public class OpenApiConfig {

}