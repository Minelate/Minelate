package dev.thezexquex.minelate.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TranslationProvider {
    private final Map<String, TranslationProject> translationProjects = new HashMap<>();

    public String getTranslation(String projectId, Locale locale, String key) {
        var translationCatalog = translationProjects.get(projectId).getTranslationCatalog(locale);
        var translation = translationCatalog.getProperty(key);

        if (translation == null) {
            return getNotAvailableTranslation(projectId, locale, key);
        } else {
            return translation;
        }
    }

    public String getNotAvailableTranslation(String projectId, Locale locale, String key) {
        return "N/A [" + projectId + "](" + locale.toLanguageTag() + ") " + key;
    }
}
