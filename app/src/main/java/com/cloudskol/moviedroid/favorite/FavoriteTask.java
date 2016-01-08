package com.cloudskol.moviedroid.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;

/**
 * @author tham
 *
 * Asyn task implementation for storing favorite movie into the database
 */
public class FavoriteTask extends AsyncTask<Movie, Void, Long> {
    private static final String LOG_TAG = FavoriteTask.class.getSimpleName();

    private Context context_;
    private MovieDbHelper dbHelper_;

    public FavoriteTask(Context context) {
        context_ = context;
        dbHelper_ = new MovieDbHelper(context_);
    }

    @Override
    protected Long doInBackground(Movie... params) {
        if (params == null || params.length == 0) {
            return null;
        }

        Movie movie = params[0];

        if (isFavoriteAlready(movie.getId())) {
            return (long)movie.getId();
        }

        final ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry._ID, movie.getId());
        values.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(MovieContract.MovieEntry.COLUMN_RATING, movie.getRating());
        values.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());

        final SQLiteDatabase database = dbHelper_.getWritableDatabase();
        long insertId = database.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);

        Log.v(LOG_TAG, "Inserted movie ID is: " + insertId);
        return insertId;
    }

    @Override
    protected void onPostExecute(Long result) {
        StringBuilder message = new StringBuilder();
        if (result == -1) {
            message.append("Problem while inserting the movie data");
        } else {
            message.append("Selected movie is added into your favorite list");
        }

        Toast.makeText(context_, message.toString(), Toast.LENGTH_SHORT).show();
    }

    private boolean isFavoriteAlready(int movieId) {
        String[] projection = {MovieContract.MovieEntry._ID};
        String selection = MovieContract.MovieEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(movieId)};

        final SQLiteDatabase database = dbHelper_.getReadableDatabase();
        final Cursor cursor = database.query(MovieContract.MovieEntry.TABLE_NAME, projection,
                selection, selectionArgs, null, null, null);
        Log.v(LOG_TAG, "Query result: " + cursor.getCount());
        return cursor.getCount() > 0;
    }
}