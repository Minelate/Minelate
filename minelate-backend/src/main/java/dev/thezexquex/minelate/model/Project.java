package dev.thezexquex.minelate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.event.PrePersist;
import io.micronaut.serde.annotation.Serdeable;

import java.security.SecureRandom;
import java.util.Base64;

@Serdeable
@MappedEntity("projects")
public class Project {

    @Id
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    @Nullable
    private String description;

    public Project() {
    }

    @PrePersist
    void generateId() {
        if (id == null) {
            byte[] randomBytes = new byte[9];
            new SecureRandom().nextBytes(randomBytes);
            id = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
