package dev.thezexquex.minelate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "player_locale")
@Serdeable
public class PlayerLocale {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("player_uuid")
    @Column(name = "player_uuid", nullable = false, length = 36)
    private UUID playerUuid;

    @JsonProperty("locale")
    @Column(name = "locale", nullable = false, length = 10)
    private String locale;

    public PlayerLocale() { }

    public PlayerLocale(UUID playerUuid, String locale) {
        this.playerUuid = playerUuid;
        this.locale = locale;
    }

    public UUID getPlayerUuid() { return playerUuid; }
    public void setPlayerUuid(UUID playerUuid) { this.playerUuid = playerUuid; }

    public String getLocale() { return locale; }
    public void setLocale(String lang) { this.locale = lang; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
