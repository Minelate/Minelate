package dev.thezexquex.minelate.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.thezexquex.minelate.api.model.PlayerLocale;
import dev.thezexquex.minelate.api.service.Constants;
import dev.thezexquex.minelate.api.service.LocaleService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class LocaleServiceIml implements LocaleService {
    private final URI minelateUri;
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Locale defaultLocale;
    private final Map<UUID, Locale> localeCache = new HashMap<>();

    public LocaleServiceIml(URI minelateUri, String apiKey, Locale defaultLocale) {
        this.minelateUri = minelateUri;
        this.apiKey = apiKey;
        this.defaultLocale = defaultLocale;
    }

    @Override
    public Locale getLocale(UUID playerUuid) {
        return localeCache.getOrDefault(playerUuid, defaultLocale);
    }

    public void fetchLocale(UUID playerUuid) {
        var request = HttpRequest.newBuilder(minelateUri.resolve(URI.create("/locale/" + playerUuid.toString())))
                .GET().header(Constants.API_KEY_HEADER, apiKey)
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
            try {
                var locale = mapper.readValue(response.body(), PlayerLocale.class);
                localeCache.put(playerUuid, Locale.forLanguageTag(locale.locale()));
            } catch (Exception e) {
                localeCache.put(playerUuid, defaultLocale);
            }
        });
    }

    public void invalidateCache(UUID playerUuid) {
        localeCache.remove(playerUuid);
    }

    @Override
    public void setLocale(UUID playerUuid, Locale locale) {
        try {
            var json = mapper.writeValueAsString(new PlayerLocale(playerUuid, locale.toLanguageTag()));
            var body = HttpRequest.BodyPublishers.ofString(json);
            var request = HttpRequest.newBuilder(minelateUri.resolve(URI.create("/locale")))
                    .POST(body)
                    .header(Constants.API_KEY_HEADER, apiKey)
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        } catch (JsonProcessingException e) {

        }
    }

    @Override
    public void deleteLocale(UUID playerUuid) {
        var request = HttpRequest.newBuilder(minelateUri.resolve(URI.create("/locale/" + playerUuid.toString())))
                .DELETE()
                .header(Constants.API_KEY_HEADER, apiKey)
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
