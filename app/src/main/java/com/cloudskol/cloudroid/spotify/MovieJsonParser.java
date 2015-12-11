package com.cloudskol.cloudroid.spotify;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class MovieJsonParser {

    private static final String LOG_TAG = MovieJsonParser.class.getSimpleName();

    private static final MovieJsonParser INSTANCE = new MovieJsonParser();

    private MovieJsonParser() {}

    static synchronized MovieJsonParser getInstance() {
        return INSTANCE;
    }

    public List<Movie> getMovies(String moviesJsonString) {
        try {
            final JSONObject moviesJson = new JSONObject(moviesJsonString);
            final JSONArray results = moviesJson.getJSONArray("results");

            Log.v(LOG_TAG,  "Size of the result array is : " + results.length());

            return getMovies(results);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing movies JSON", e);
        }

        return null;
    }

    public Movie getMovie(String movieJsonString) {
        Movie movie = null;
        try {
            movie = getMovie(new JSONObject(movieJsonString));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing movie JSON", e);
            return null;
        }

        return movie;
    }

    private List<Movie> getMovies(JSONArray moviesJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>(moviesJsonArray.length());

        for (int i = 0; i < moviesJsonArray.length(); i++) {
            movies.add(getMovie(moviesJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    private Movie getMovie(JSONObject movieJson) throws JSONException {
        Movie movie = new Movie(movieJson.getInt(MovieJson.ID.getValue()),
                movieJson.getString(MovieJson.TITLE.getValue()));
        movie.setOverview(movieJson.getString(MovieJson.OVERVIEW.getValue()));
        movie.setPoster(movieJson.getString(MovieJson.POSTER.getValue()));
        movie.setBackdrop(movieJson.getString(MovieJson.BACKDROP.getValue()));
        Double ratingDouble = (Double)movieJson.get(MovieJson.RATING.getValue());
        movie.setRating(ratingDouble.floatValue()/2); //Rating will be considered based on 5 stars
        movie.setReleaseDate(movieJson.getString(MovieJson.RELEASE_DATE.getValue()));
        return movie;
    }
}
