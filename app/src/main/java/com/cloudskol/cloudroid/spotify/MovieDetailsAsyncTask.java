package com.cloudskol.cloudroid.spotify;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.cloudroid.common.CloudroidException;

/**
 * @author tham
 *
 * Async task handler for fetching the movie details from the movie API
 */
public class MovieDetailsAsyncTask extends AsyncTask<Uri, Void, Movie> {

    private static final String LOG_TAG = MovieDetailsAsyncTask.class.getSimpleName();

    private Context context_;

    public MovieDetailsAsyncTask(Context context) {
        context_ = context;
    }

    @Override
    protected Movie doInBackground(Uri... Uris) {
        if (Uris.length <= 0) {
            return null;
        }

        String movieDetailsJsonString;
        final Uri movieDetailsUri = Uris[0];
        final SpotifyUrlConnector spotifyUrlConnector = new SpotifyUrlConnector();
        try {
            movieDetailsJsonString = spotifyUrlConnector.getJson(movieDetailsUri);
        } catch (CloudroidException e) {
            Log.e(LOG_TAG, "Error while fetching movie details", e);
            return null;
        }

        Log.v(LOG_TAG, movieDetailsJsonString);
        return MovieJsonParser.getInstance().getMovie(movieDetailsJsonString);
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);

        if (context_ instanceof MovieDetailsActivity) {
            ((MovieDetailsActivity)context_).onMovieDataReceived(movie);
        }
    }
}
