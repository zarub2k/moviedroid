package com.cloudskol.cloudroid.common;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Properties;

/**
 * @author tham
 *
 * Property reader for Cloudroid application
 */
public class CloudroidPropertyReader {

    private static final String LOG_TAG = CloudroidPropertyReader.class.getSimpleName();

    private static final CloudroidPropertyReader INSTANCE = new CloudroidPropertyReader();
    private static final String PROPERTY_FILE = "cloudroid.properties";

    private static Properties properties;
    private Context context;

    private CloudroidPropertyReader() {
        properties = new Properties();
    }

    public static final CloudroidPropertyReader getInstance(Context context) {
        if (!properties.isEmpty()) {
            return INSTANCE;
        }

        try {
            properties.load(context.getAssets().open(PROPERTY_FILE));
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error while loading cloudroid property", e);
        }

        return INSTANCE;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
