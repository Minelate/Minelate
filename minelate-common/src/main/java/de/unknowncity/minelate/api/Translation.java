package de.unknowncity.minelate.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Locale;

public abstract class Translation<P> {
    private final String projectId;
    private TranslationProvider translationProvider;
    private LocaleProvider localeProvider;

    protected Translation(String projectId) {
        this.projectId = projectId;
    }

    public abstract Component component(P player, String key, TagResolver... tagResolvers);

    public abstract Component component(Locale locale, String key, TagResolver... tagResolvers);

    protected abstract String applyPapiPlaceholders(String translationString, P player);
}
