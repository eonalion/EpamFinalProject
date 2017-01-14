package com.suboch.manager;

/**
 *
 */

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static String FILE_PATH = "properties.configuration";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_PATH);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
