package dev.thezexquex.minelate.config;

import io.micronaut.context.annotation.EachProperty;
import jakarta.annotation.Nonnull;

@EachProperty("api-keys")
public interface ApiKeyConfiguration {

    @Nonnull
    String getName();

    @Nonnull
    String getKey();
}

