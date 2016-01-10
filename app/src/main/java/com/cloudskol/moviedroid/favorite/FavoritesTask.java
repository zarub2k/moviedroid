package com.cloudskol.moviedroid.favorite;

import android.os.AsyncTask;

import com.cloudskol.moviedroid.model.Movie;

import java.util.List;

/**
 * @author tham
 */
public class FavoritesTask extends AsyncTask<Void, Void, List<Movie>> {

    private static final String LOG_TAG = FavoritesTask.class.getSimpleName();

    @Override
    protected List<Movie> doInBackground(Void... params) {
        return null;
    }
}
