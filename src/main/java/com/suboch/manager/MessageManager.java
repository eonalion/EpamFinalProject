package com.suboch.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 */
public class MessageManager {
    private static final Locale ENGLISH = new Locale("en", "US");
    private static final String FILE_PATH = "properties.content";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_PATH, ENGLISH);
    private static Locale currentLocale = ENGLISH;

    private MessageManager() {
    }

    public static String getProperty(String key, Locale locale) {
        if (locale != null) {
            if (!currentLocale.equals(locale)) {
                    resourceBundle = ResourceBundle.getBundle(FILE_PATH, locale);
            }
        } else {
            resourceBundle = ResourceBundle.getBundle(FILE_PATH);
        }
        return resourceBundle.getString(key);
    }

}