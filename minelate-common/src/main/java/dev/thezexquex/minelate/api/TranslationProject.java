package dev.thezexquex.minelate.api;

import java.util.*;

public class TranslationProject {
    private final String projectId;
    private final Map<Locale, ResourceBundle> translations = new HashMap<>();

    public TranslationProject(String projectId, Map<Locale, ResourceBundle> translations) {
        this.projectId = projectId;
        this.translations.putAll(translations);
    }

    public ResourceBundle getTranslationCatalog(Locale locale) {
        return translations.get(locale);
    }

    public String projectId() {
        return projectId;
    }
}
