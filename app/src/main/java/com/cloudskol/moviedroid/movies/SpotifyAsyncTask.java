package com.cloudskol.moviedroid.movies;

import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;

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
        } catch (MoviedroidException e) {
            Log.e(LOG_TAG, "Error while fetching JSON", e);
            return null;
        }

        Log.v(LOG_TAG, moviesJsonString);

        return moviesJsonString;
    }
}
