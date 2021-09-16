package com.infinira.hr.util;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;


public class ResourceService {

    private volatile static ResourceService instance = null;
    private ResourceBundle resource;

    private final String PROPERTIES_FILE = "hrmessages";

    public static ResourceService getInstance() {
        if ( instance == null ) {
            synchronized(ResourceService.class) {
                if ( instance == null ) {
                    instance = new ResourceService();
                }
            }
        }
        return instance;
    }

    private ResourceService() {
        try {
			resource = ResourceBundle.getBundle(PROPERTIES_FILE, Locale.getDefault());
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public String getMessage(String key, Object... params) {
        if (key == null || key.isEmpty()) {
            return NULL_KEY;
        }
        if (!resource.containsKey( key )) {
            return INVALID_KEY + key;
        }
        String message = null;
        try {
            message = resource.getString(key);
            if (message.isEmpty()){
                message = MessageFormat.format(EMPTY_MESSAGE, key);
            } else {
                if (params.length > 0){
                    message = MessageFormat.format(message, params);
                }
            }
        } catch (Throwable th) {
            message = MessageFormat.format(FAILED_MESSAGE, key, message);
        }
        return message;
    }

    private final String INVALID_KEY = "Can not Find this key: ";
    private final String EMPTY_MESSAGE = "This key has an empty message: {0} ";
    private final String NULL_KEY = "Key can not be null or Empty";
    private final String FAILED_MESSAGE = "Failed to format the message for the key {0} and message = {1}";

}

