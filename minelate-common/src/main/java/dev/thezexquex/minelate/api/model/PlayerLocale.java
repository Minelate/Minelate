package dev.thezexquex.minelate.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PlayerLocale {
    @JsonProperty("player_uuid")
    private UUID player;
    @JsonProperty("locale")
    private String locale;

    public PlayerLocale() {

    }

    public PlayerLocale(UUID player, String locale) {
        this.player = player;
        this.locale = locale;
    }


    public UUID player() {
        return player;
    }

    public String locale() {
        return locale;
    }
}
