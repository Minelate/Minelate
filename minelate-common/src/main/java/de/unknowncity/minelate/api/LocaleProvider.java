package de.unknowncity.minelate.api;

import java.util.Locale;

public interface LocaleProvider<P> {
    Locale getPlayerLocale(P player);
}
