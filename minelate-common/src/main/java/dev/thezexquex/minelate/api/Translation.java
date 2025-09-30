package dev.thezexquex.minelate.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public  class Translation<P> {
    private final TranslationProvider<P> translationProvider;
    private final LocaleProvider<P> localeProvider;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final String projectId;
    public Translation(String projectId, TranslationProvider<P> translationProvider, LocaleProvider<P> localeProvider) {
        this.projectId = projectId;
        this.translationProvider = translationProvider;
        this.localeProvider = localeProvider;
    }

    public Component component(P player, String key, TagResolver... tagResolvers) {
        return component(localeProvider.getPlayerLocale(player), key, tagResolvers);
    }

    public Component component(Locale locale, String key, TagResolver... tagResolvers) {
        var translation = translationProvider.getTranslation(projectId, locale, key);
        return miniMessage.deserialize(translation, tagResolvers);
    }

    public List<Component> componentList(P player, String key, TagResolver... tagResolvers) {
        return componentList(localeProvider.getPlayerLocale(player), key, tagResolvers);
    }

    public List<Component> componentList(Locale locale, String key, TagResolver... tagResolvers) {
        var translation = translationProvider.getTranslation(projectId, locale, key);
        var translationList = translation.split("\\n");
        return Arrays.stream(translationList).map(string -> miniMessage.deserialize(string, tagResolvers)).toList();
    }
}
