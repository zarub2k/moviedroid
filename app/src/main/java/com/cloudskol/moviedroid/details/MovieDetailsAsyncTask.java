package com.cloudskol.moviedroid.details;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;
import com.cloudskol.moviedroid.common.MoviedroidUrlConnector;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.parser.MovieJsonParser;

/**
 * @author tham
 *
 * Async task handler for fetching the movie details from the movie API
 */
public class MovieDetailsAsyncTask extends AsyncTask<Uri, Void, Movie> {

    private static final String LOG_TAG = MovieDetailsAsyncTask.class.getSimpleName();

    private DetailFragment detailFragment_;

    public MovieDetailsAsyncTask(DetailFragment detailFragment) {
        detailFragment_ = detailFragment;
    }

    @Override
    protected Movie doInBackground(Uri... Uris) {
        if (Uris.length <= 0) {
            return null;
        }

        String movieDetailsJsonString;
        final Uri movieDetailsUri = Uris[0];
        final MoviedroidUrlConnector moviedroidUrlConnector = new MoviedroidUrlConnector();
        try {
            movieDetailsJsonString = moviedroidUrlConnector.getJson(movieDetailsUri);
        } catch (MoviedroidException e) {
            Log.e(LOG_TAG, "Error while fetching movie details", e);
            return null;
        }

        Log.v(LOG_TAG, movieDetailsJsonString);
        return MovieJsonParser.getInstance().getMovie(movieDetailsJsonString);
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);

        if (detailFragment_ instanceof DetailFragment) {
            ((DetailFragment)detailFragment_).onMovieDataReceived(movie);
        }
    }
}
