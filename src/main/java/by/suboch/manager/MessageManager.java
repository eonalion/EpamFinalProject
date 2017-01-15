package by.suboch.manager;

import java.util.ResourceBundle;

/**
 *
 */
public class MessageManager {
    private static final String MESSAGE_FILE_PATH = "properties.message";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGE_FILE_PATH);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}