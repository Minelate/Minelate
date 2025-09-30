package dev.thezexquex.minelate.api.service;

import java.util.Locale;
import java.util.UUID;

public interface LocaleService {

    /**
     * Retrieves the locale settings associated with the given player's unique identifier.
     *
     * @param playerUuid the unique identifier of the player whose locale is to be retrieved
     * @return the locale associated with the specified player, or a default locale
     *         if no specific locale is set for the player
     */
    Locale getLocale(UUID playerUuid);

    void fetchLocale(UUID playerUuid);

    void invalidateCache(UUID playerUuid);

    /**
     * Sets the locale for a specific player identified by their unique identifier.
     *
     * @param playerUuid the unique identifier of the player whose locale is to be set
     * @param locale the locale to assign to the specified player
     */
    void setLocale(UUID playerUuid, Locale locale);

    /**
     * Deletes the locale associated with the specified player's unique identifier.
     * This effectively removes any custom locale setting for the player.
     *
     * @param playerUuid the unique identifier of the player whose locale is to be deleted
     */
    void deleteLocale(UUID playerUuid);
}
