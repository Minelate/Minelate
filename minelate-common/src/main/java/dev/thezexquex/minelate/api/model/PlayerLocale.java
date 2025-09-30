package dev.thezexquex.minelate.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PlayerLocale(
        @JsonProperty("player_uuid")
        UUID player,
        @JsonProperty("locale")
        String locale
) {

}
