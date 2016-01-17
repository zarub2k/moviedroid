package com.cloudskol.moviedroid.favorite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.list.MoviesGridAdapter;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;
import com.cloudskol.moviedroid.provider.MovieDbManager;
import com.cloudskol.moviedroid.provider.MovieProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class FavoritesTask extends AsyncTask<Void, Void, Cursor> {

    private static final String LOG_TAG = FavoritesTask.class.getSimpleName();

    private MoviesGridAdapter gridAdapter_;
    private Context context_;

    public FavoritesTask(MoviesGridAdapter gridAdapter, Context context) {
        gridAdapter_ = gridAdapter;
        context_ = context;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        return context_.getContentResolver().query(MovieProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        gridAdapter_.clear();
        gridAdapter_.addAll(new MovieDbManager().getAll(cursor));
    }
}