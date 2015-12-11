package com.cloudskol.moviedroid.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;

import java.util.List;

/**
 * @author tham
 *
 * Async task handler for fetching movies list from the remote Uri
 */
public class DiscoverMoviesAsyncTask extends AsyncTask<Uri, Void, List<Movie>> {

    private static final String LOG_TAG = DiscoverMoviesAsyncTask.class.getSimpleName();

    private MoviesGridAdapter moviesGridAdapter_;

    public DiscoverMoviesAsyncTask(MoviesGridAdapter moviesGridAdapter) {
        moviesGridAdapter_ = moviesGridAdapter;
    }

    @Override
    protected List<Movie> doInBackground(Uri... Uris) {
        if (Uris.length <= 0) {
            return null;
        }

        final String moviesJsonString;
        Uri discoverMoviesUri = Uris[0];
        final SpotifyUrlConnector spotifyUrlConnector = new SpotifyUrlConnector();
        try {
            moviesJsonString = spotifyUrlConnector.getJson(discoverMoviesUri.toString());
        } catch (MoviedroidException e) {
            Log.e(LOG_TAG, "Error while fetching JSON: " + e.getMessage(), e);
            return null;
        }

        Log.v(LOG_TAG, moviesJsonString);

        return MovieJsonParser.getInstance().getMovies(moviesJsonString);
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return;
        }

        moviesGridAdapter_.clear();
        moviesGridAdapter_.addAll(movies);
    }
}
