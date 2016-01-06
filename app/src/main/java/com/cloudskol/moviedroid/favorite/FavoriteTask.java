package com.cloudskol.moviedroid.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;

/**
 * @author tham
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

        Movie selectedMovie = params[0];
        final ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry._ID, selectedMovie.getId());
        values.put(MovieContract.MovieEntry.COLUMN_TITLE, selectedMovie.getTitle());
        values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, selectedMovie.getOverview());
        values.put(MovieContract.MovieEntry.COLUMN_RATING, selectedMovie.getRating());
        values.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, selectedMovie.getReleaseDate());

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
}
