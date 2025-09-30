package dev.thezexquex.minelate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ResourceBundleLoader {

    public static Map<Locale, ResourceBundle> loadBundles(Path folder) {
        Map<Locale, ResourceBundle> bundles = new HashMap<>();

        if (!Files.exists(folder)) {
            folder.toFile().mkdirs();
        }

        for (File file : folder.toFile().listFiles((dir, name) -> name.endsWith(".properties"))) {
            var fileName = file.getName();
            var localePart = fileName.substring(0, fileName.length() - ".properties".length());
            var locale = parseLocale(localePart);

            try (FileInputStream inputStream = new FileInputStream(file)) {
                var bundle = new PropertyResourceBundle(inputStream);
                bundles.put(locale, bundle);
            } catch (IOException e) {
                return Map.of();
            }
        }
        return bundles;
    }

    private static Locale parseLocale(String localeString) {
        return Locale.forLanguageTag(localeString.replace('_', '-'));
    }
}
