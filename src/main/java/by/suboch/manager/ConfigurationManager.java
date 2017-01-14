package by.suboch.manager;

/**
 *
 */

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final String CONFIGURATION_FILE_PATH = "properties.configuration";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIGURATION_FILE_PATH);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
