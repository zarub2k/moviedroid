package com.cloudskol.moviedroid.favorite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;

import java.util.List;

/**
 * @author tham
 */
public class FavoritesTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String LOG_TAG = FavoritesTask.class.getSimpleName();

    private Context context_;
    private MovieDbHelper dbHelper_;

    public FavoritesTask(Context context, MovieDbHelper dbHelper) {
        context_ = context;
        dbHelper_ = dbHelper;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        getFavoriteMovies();
        return null;
    }

    private void getFavoriteMovies() {
        final SQLiteDatabase database = dbHelper_.getReadableDatabase();
        final Cursor cursor = database.query(MovieContract.MovieEntry.TABLE_NAME,
                null, null, null, null, null, null);
        Log.v(LOG_TAG, "Size of the cursor is: " + cursor.getCount());
    }
}
