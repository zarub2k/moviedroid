package com.cloudskol.moviedroid.favorite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class FavoritesTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String LOG_TAG = FavoritesTask.class.getSimpleName();

    private FavoritesActivity context_;
    private MovieDbHelper dbHelper_;

    public FavoritesTask(FavoritesActivity context, MovieDbHelper dbHelper) {
        context_ = context;
        dbHelper_ = dbHelper;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        getFavoriteMovies();
        return null;
    }

    private List<Movie> getFavoriteMovies() {
        final SQLiteDatabase database = dbHelper_.getReadableDatabase();
        final Cursor cursor = database.query(MovieContract.MovieEntry.TABLE_NAME,
                null, null, null, null, null, null);
        final int count = cursor.getCount();
        Log.v(LOG_TAG, "Size of the cursor is: " + count);

        if (count == 0) {
            return null;
        }

        cursor.moveToFirst();
        List<Movie> favoriteMovies = new ArrayList<Movie>(count);
        for (int i = 0; i < count; i++) {
            favoriteMovies.add(getMovie(cursor));
            cursor.moveToNext();
        }

        return favoriteMovies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        context_.onFavoriteMoviesReceived(movies);
    }

    private Movie getMovie(Cursor cursor) {
        final long id = cursor.getLong(cursor.getColumnIndexOrThrow(
                MovieContract.MovieEntry._ID));
        final String title = cursor.getString(cursor.getColumnIndexOrThrow(
                MovieContract.MovieEntry.COLUMN_TITLE));

        Log.v(LOG_TAG, title + " > " + id);

        final Movie movie = new Movie((int) id, title);
        return movie;
    }
}
