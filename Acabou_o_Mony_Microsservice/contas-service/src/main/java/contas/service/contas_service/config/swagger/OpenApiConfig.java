package contas.service.contas_service.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto Clientes",
                description = "implementação de JWT com Spring Security",
                contact = @Contact(
                        name = "Diego",
                        url = "https://github.com/PedroTrovoSouza/Acabou-o-Mony-Squad2",
                        email = "joao.rossi@gmail.com"
                ),
                license = @License(name = "UNLICENSED"),
                version = "1.0.0"
        )
)
@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)
public class OpenApiConfig {
}
