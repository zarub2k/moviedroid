package com.cloudskol.moviedroid.parser;

import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;
import com.cloudskol.moviedroid.model.Trailer;
import com.cloudskol.moviedroid.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 *
 * Parser for trailers of a movie.
 * The parser will take trailers json string as an input and parse the content
 *
 * If there is no videos available for any given movie, the trailer will be returned as null
 */
public class TrailerJsonParser {
    private static final String LOG_TAG = TrailerJsonParser.class.getSimpleName();

    private static final TrailerJsonParser INSTANCE = new TrailerJsonParser();

    private TrailerJsonParser() {}

    public static final synchronized TrailerJsonParser getInstance() {
        return INSTANCE;
    }

    public Trailer getTrailer(String trailerJsonString) {
        try {
            final JSONObject trailerJson = new JSONObject(trailerJsonString);
            return getTrailer(trailerJson);

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing trailerJsonString", e);
        }

        return null;
    }

    private Trailer getTrailer(JSONObject trailerJson) throws JSONException {
        if (trailerJson == null) {
            return null;
        }

        final JSONArray videosJsonArray = trailerJson.getJSONArray(TrailerJsonKeys.RESULTS.getValue());
        if (videosJsonArray == null || videosJsonArray.length() == 0) {
            return null;
        }

        List<Video> videos = new ArrayList<Video>(videosJsonArray.length());
        JSONObject videoJson;
        for (int i = 0; i < videosJsonArray.length(); i++) {
            videoJson = (JSONObject) videosJsonArray.get(i);
            videos.add(new Video(videoJson.getString(TrailerJsonKeys.KEY.getValue()),
                    videoJson.getString(TrailerJsonKeys.NAME.getValue())));

        }

        return new Trailer(trailerJson.getInt(TrailerJsonKeys.ID.getValue()), videos);
    }
}
