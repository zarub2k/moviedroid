package com.cloudskol.cloudroid.spotify;

import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.cloudroid.common.CloudroidException;

/**
 * @author tham
 */
public class SpotifyAsyncTask extends AsyncTask<String, Void, String> {

    private final String LOG_TAG = SpotifyAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(String... params) {
        if (params.length <= 0) {
            return null;
        }

        String moviesJsonString = null;
        String path = params[0];
        final SpotifyUrlConnector spotifyUrlConnector = new SpotifyUrlConnector();
        try {
            moviesJsonString = spotifyUrlConnector.getJson(path);
        } catch (CloudroidException e) {
            Log.e(LOG_TAG, "Error while fetching JSON", e);
            return null;
        }

        Log.v(LOG_TAG, moviesJsonString);

        return moviesJsonString;
    }
}
