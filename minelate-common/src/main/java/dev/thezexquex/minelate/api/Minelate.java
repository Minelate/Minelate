package dev.thezexquex.minelate.api;

import dev.thezexquex.minelate.api.service.MinelateAPI;

public class Minelate {
    private static MinelateAPI instance;

    public static <P> MinelateAPI<P> getInstance() {
        return instance;
    }

    public static <P> void setInstance(MinelateAPI<P> instance) {
        Minelate.instance = instance;
    }
}
