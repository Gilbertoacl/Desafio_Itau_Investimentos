package com.itau.desafio.investimentos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Investimentos - Desafio Itaú")
                        .contact(new Contact().name("Gilberto de Amorim").email("gilberto.consule@hotmail.com"))
                        .version("1.0")
                        .description("Sistema de controle de operações, posições e cotações."));
    }
}
