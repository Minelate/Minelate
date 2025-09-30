package dev.thezexquex.minelate.api;

import dev.thezexquex.minelate.ResourceBundleLoader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TranslationProvider<P> {
    protected final Map<String, TranslationProject> translationProjects = new HashMap<>();
    protected final Path dataPath;
    protected final LocaleProvider<P> localeProvider;

    public TranslationProvider(Path dataPath, LocaleProvider<P> localeProvider) {
        this.dataPath = dataPath;
        this.localeProvider = localeProvider;
    }

    public Translation<P> registerTranslationProject(String projectId) {
        var bundles = ResourceBundleLoader.loadBundles(dataPath.resolve("language").resolve(projectId));
        var translationProject = new TranslationProject(projectId, bundles);
        translationProjects.put(translationProject.projectId(), translationProject);
        return new Translation<>(projectId, this, localeProvider);
    }

    public void reloadTranslations() {
        translationProjects.forEach((projectId, translationProject) -> {
            var bundles = ResourceBundleLoader.loadBundles(dataPath.resolve("language").resolve(projectId));
            var newTranslationProject = new TranslationProject(projectId, bundles);
            translationProjects.put(translationProject.projectId(), newTranslationProject);
        });
    }

    public String getTranslation(String projectId, Locale locale, String key) {
        var translationCatalog = translationProjects.get(projectId).getTranslationCatalog(locale);
        String translation;

        try {
            translation = translationCatalog.getString(key);
        } catch (Exception e) {
            translation = getNotAvailableTranslation(projectId, locale, key);
        }

        return translation;
    }

    public String getNotAvailableTranslation(String projectId, Locale locale, String key) {
        return "N/A [" + projectId + "](" + locale.toLanguageTag() + ") " + key;
    }
}
