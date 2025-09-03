package de.unknowncity.minelate.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class TranslationProject {
    private final String projectId;
    private final Map<Locale, Properties> translations = new HashMap<>();

    public TranslationProject(String projectId) {
        this.projectId = projectId;
    }

    public Properties getTranslationCatalog(Locale locale) {
        return translations.get(locale);
    }
}
