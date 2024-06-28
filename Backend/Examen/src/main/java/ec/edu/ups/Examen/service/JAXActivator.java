package ec.edu.ups.Examen.service;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@io.swagger.v3.oas.annotations.OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "API Examen",
        version = "1.0",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
            name = "Anthony",
            email = "tu_email@dominio.com",
            url = "https://www.example.com"
        )
    )
)
public class JAXActivator extends Application {
}
