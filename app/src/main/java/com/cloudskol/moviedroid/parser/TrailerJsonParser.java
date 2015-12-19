package com.cloudskol.moviedroid.parser;

import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;
import com.cloudskol.moviedroid.model.Trailer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author tham
 */
public class TrailerJsonParser {
    private static final String LOG_TAG = TrailerJsonParser.class.getSimpleName();

    private static final TrailerJsonParser INSTANCE = new TrailerJsonParser();

    private TrailerJsonParser() {}

    public static final synchronized TrailerJsonParser getInstance() {
        return INSTANCE;
    }

    public Trailer getTrailer(String trailerJsonString) throws MoviedroidException {
        try {
            final JSONObject trailerJson = new JSONObject(trailerJsonString);
            trailerJson.getJSONArray("results");
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing trailerJsonString", e);
            throw new MoviedroidException(e);
        }
        return null;
    }
}
