package dev.thezexquex.minelate.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.thezexquex.minelate.api.model.PlayerLocale;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocaleServiceIml implements LocaleService {
    private final URI minelateUri;
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Locale defaultLocale;
    private final Map<UUID, Locale> localeCache = new HashMap<>();
    private static final Logger logger = Logger.getLogger(LocaleServiceIml.class.getName());

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
        var uri = minelateUri.resolve("locale/").resolve(playerUuid.toString());
        System.out.println(minelateUri);
        System.out.println(uri);
        var request = HttpRequest.newBuilder(uri)
                .GET().header(Constants.API_KEY_HEADER, apiKey)
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).whenComplete((response, throwable)  -> {
            if (throwable != null) {
                logger.log(Level.WARNING, "Failed to fetch locale for player " + playerUuid, throwable);
                localeCache.put(playerUuid, defaultLocale);
                return;
            }
            try {
                var locale = mapper.readValue(response.body(), PlayerLocale.class);
                localeCache.put(playerUuid, Locale.forLanguageTag(locale.locale()));
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to fetch locale for player " + playerUuid, e);
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
            var uri = minelateUri.resolve("locale/");
            var json = mapper.writeValueAsString(new PlayerLocale(playerUuid, locale.toLanguageTag()));
            var body = HttpRequest.BodyPublishers.ofString(json);
            var request = HttpRequest.newBuilder(uri)
                    .POST(body)
                    .header(Constants.API_KEY_HEADER, apiKey)
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            localeCache.put(playerUuid, locale);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Failed to set locale for player " + playerUuid, e);
        }
    }

    @Override
    public void deleteLocale(UUID playerUuid) {
        var uri = minelateUri.resolve("locale/").resolve(playerUuid.toString());
        var request = HttpRequest.newBuilder(uri)
                .DELETE()
                .header(Constants.API_KEY_HEADER, apiKey)
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
