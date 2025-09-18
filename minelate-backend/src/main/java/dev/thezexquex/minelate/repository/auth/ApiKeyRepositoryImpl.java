package dev.thezexquex.minelate.repository.auth;

import dev.thezexquex.minelate.config.ApiKeyConfiguration;
import jakarta.inject.Singleton;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Singleton 
class ApiKeyRepositoryImpl implements ApiKeyRepository {

    private final Map<String, Principal> keys;

    ApiKeyRepositoryImpl(List<ApiKeyConfiguration> apiKeys) {
        keys = new HashMap<>();
        for (ApiKeyConfiguration configuration : apiKeys) {
            keys.put(configuration.getKey(), configuration::getName);
        }
    }

    @Override
    @NonNull
    public Optional<Principal> findByApiKey(@NonNull @NotBlank String apiKey) {
        return Optional.ofNullable(keys.get(apiKey));
    }
}