package com.cloudskol.moviedroid.favorite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.list.MoviesGridAdapter;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieContract;
import com.cloudskol.moviedroid.provider.MovieDbHelper;
import com.cloudskol.moviedroid.provider.MovieDbManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class FavoritesTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String LOG_TAG = FavoritesTask.class.getSimpleName();

    private MoviesGridAdapter context_;
    private MovieDbManager dbManager_;

    public FavoritesTask(MoviesGridAdapter context, MovieDbHelper dbHelper) {
        context_ = context;
        dbManager_ = new MovieDbManager(dbHelper);
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        return getFavoriteMovies();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        context_.clear();
        context_.addAll(movies);
    }

    private List<Movie> getFavoriteMovies() {
        return dbManager_.getAll();
    }
}
