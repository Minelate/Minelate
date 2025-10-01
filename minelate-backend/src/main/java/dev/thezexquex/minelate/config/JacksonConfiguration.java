package dev.thezexquex.minelate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class JacksonConfiguration {

    @Bean
    @Singleton
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}