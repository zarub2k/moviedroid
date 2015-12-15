package com.cloudskol.moviedroid.common;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Properties;

/**
 * @author tham
 *
 * Property reader for Cloudroid application
 */
public class MoviedroidPropertyReader {

    private static final String LOG_TAG = MoviedroidPropertyReader.class.getSimpleName();

    private static final MoviedroidPropertyReader INSTANCE = new MoviedroidPropertyReader();
    private static final String PROPERTY_FILE = "moviedroid.properties";

    private static Properties properties;
    private Context context;

    private MoviedroidPropertyReader() {
        properties = new Properties();
    }

    public static final MoviedroidPropertyReader getInstance(Context context) {
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
