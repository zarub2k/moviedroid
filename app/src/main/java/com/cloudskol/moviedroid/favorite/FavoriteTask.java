package com.cloudskol.moviedroid.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;
import com.cloudskol.moviedroid.provider.MovieProvider;

/**
 * @author tham
 *
 * Asyn task implementation for storing favorite movie into the database
 */
public class FavoriteTask extends AsyncTask<Movie, Void, Long> {
    private static final String LOG_TAG = FavoriteTask.class.getSimpleName();

    private Context context_;

    public FavoriteTask(Context context) {
        context_ = context;
    }

    @Override
    protected Long doInBackground(Movie... params) {
        if (params == null || params.length == 0) {
            return null;
        }

        Movie movie = params[0];

        if (isFavoriteAlready(movie.getId())) {
            return Long.valueOf(0);
        }

        final ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry._ID, movie.getId());
        values.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(MovieContract.MovieEntry.COLUMN_RATING, movie.getRating());
        values.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());

        final Uri insertedUri = context_.getContentResolver().insert(MovieProvider.CONTENT_URI, values);
        if (insertedUri != null) {
            Log.v(LOG_TAG, "Inserted movie ID is: " + movie.getId());
            return Long.valueOf(movie.getId());
        }

        return Long.valueOf(-1);
    }

    @Override
    protected void onPostExecute(Long result) {
        StringBuilder message = new StringBuilder();
        if (result == 0) {
            message.append("Selected movie is already added into your favorite list");
        } else if (result == -1) {
            message.append("Problem while inserting the movie data");
        } else {
            message.append("Movie is added into your favorite list");
        }

        Toast.makeText(context_, message.toString(), Toast.LENGTH_SHORT).show();
    }

    private boolean isFavoriteAlready(int movieId) {
        Log.v(LOG_TAG, "Selected movie id is: " + movieId);
        String[] projection = {MovieContract.MovieEntry._ID};
        String selection = MovieContract.MovieEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(movieId)};

        final Uri movieIdUri = Uri.parse(MovieProvider.CONTENT_URI.toString() + "/" + movieId);
        final Cursor cursor = context_.getContentResolver().query(movieIdUri,
                projection, selection, selectionArgs, null);

        Log.v(LOG_TAG, "Movies available in the favorites list: " + cursor.getCount());
        return cursor.getCount() > 0;
    }
}