package com.cloudskol.moviedroid.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

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
        final ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.MovieEntry._ID, selectedMovie.getId());
        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, selectedMovie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, selectedMovie.getOverview());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, selectedMovie.getRating());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, selectedMovie.getReleaseDate());

        final SQLiteDatabase database = dbHelper_.getWritableDatabase();
        database.save
        return null;
    }
}
