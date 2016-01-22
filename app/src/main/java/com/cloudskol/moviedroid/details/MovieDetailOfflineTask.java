package com.cloudskol.moviedroid.details;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbManager;
import com.cloudskol.moviedroid.provider.MovieProvider;

/**
 * @author tham
 */
public class MovieDetailOfflineTask extends AsyncTask<Integer, Void, Movie> {

    private Context context;
    private DetailFragment fragment;
    public MovieDetailOfflineTask(Context context, DetailFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    protected Movie doInBackground(Integer... params) {
        if (params == null || params.length == 0) {
            return null;
        }

        int movieId = params[0].intValue();

        String selection = MovieContract.MovieEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(movieId)};

        final Uri movieIdUri = Uri.parse(MovieProvider.CONTENT_URI.toString() + "/" + movieId);
        final Cursor cursor = context.getContentResolver().query(movieIdUri,
                null, selection, selectionArgs, null);
        if (cursor.getCount() == 0) {
            return null;
        }

        cursor.moveToFirst();

        final MovieDbManager movieDbManager = new MovieDbManager();
        return movieDbManager.getMovie(cursor);
    }

    @Override
    protected void onPostExecute(Movie movie) {
        fragment.onMovieDataReceived(movie);
    }
}
