package dev.thezexquex.minelate.auth;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.token.DefaultRolesFinder;
import io.micronaut.security.token.RolesFinder;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;

@Singleton
@Replaces(DefaultRolesFinder.class)
public class AuthentikRolesFinder implements RolesFinder {
    @Override
    public @NonNull List<String> resolveRoles(@Nullable Map<String, Object> attributes) {
        return List.of();
    }
}
