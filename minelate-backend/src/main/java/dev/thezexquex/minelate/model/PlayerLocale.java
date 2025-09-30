package dev.thezexquex.minelate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@MappedEntity("player_locale")
@Serdeable
public record PlayerLocale(
        @Id
        @MappedProperty("player_uuid")
        @JsonProperty("player_uuid")
        UUID playerUuid,

        @MappedProperty("locale")
        @JsonProperty("locale")
        String locale
) {}
