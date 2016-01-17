package com.cloudskol.moviedroid.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudskol.moviedroid.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 *
 * Helper class for communicating with database
 */
public class MovieDbManager {
    private static final String LOG_TAG = MovieDbManager.class.getSimpleName();

    private static final int PAGE_SIZE = 20;

    public List<Movie> getAll(Cursor cursor) {
        int count = cursor.getCount();
        Log.v(LOG_TAG, "Cursor count is: " + count);

        if (count == 0) {
            return new ArrayList<Movie>(2);
        }

        if (count > PAGE_SIZE) { //default page size should be respected
            count = PAGE_SIZE;
        }

        cursor.moveToFirst();
        List<Movie> movies = new ArrayList<Movie>(count);
        for (int i = 0; i < count; i++) {
            movies.add(getMovie(cursor));
            cursor.moveToNext();
        }

        closeCursor(cursor);
        return movies;
    }

    private void closeCursor(Cursor cursor) {
        if (cursor.isClosed()) {
            return;
        }

        cursor.close();
    }

    private Movie getMovie(Cursor cursor) {
        final long id = cursor.getLong(cursor.getColumnIndexOrThrow(
                MovieContract.MovieEntry._ID));
        final String title = cursor.getString(cursor.getColumnIndexOrThrow(
                MovieContract.MovieEntry.COLUMN_TITLE));
        final String overview = cursor.getString(cursor.getColumnIndexOrThrow(
                MovieContract.MovieEntry.COLUMN_OVERVIEW));

        Log.v(LOG_TAG, title + " > " + id);

        final Movie movie = new Movie((int) id, title);
        movie.setOverview(overview);
        return movie;
    }
}
