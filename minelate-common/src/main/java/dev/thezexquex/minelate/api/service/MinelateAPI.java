package dev.thezexquex.minelate.api.service;

import dev.thezexquex.minelate.api.TranslationProvider;

public record MinelateAPI<P>(
        TranslationProvider<P> translationProvider
) {

}
