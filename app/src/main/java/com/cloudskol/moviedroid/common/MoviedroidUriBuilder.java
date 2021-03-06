package com.cloudskol.moviedroid.common;

import android.net.Uri;
import android.util.Log;

import com.cloudskol.moviedroid.model.SortBy;

/**
 * @author tham
 *
 * Utility class for building Uri for list of movies and movie details
 */
public class MoviedroidUriBuilder {

    private static final String LOG_TAG = MoviedroidUriBuilder.class.getSimpleName();

    private MoviedroidPropertyReader propertyReader_;

    public MoviedroidUriBuilder(MoviedroidPropertyReader propertyReader) {
        propertyReader_ = propertyReader;
    }

    public Uri discoverMoviesUri(SortBy sortBy) {
        Uri discoverMoviesUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_DISCOVER_MOVIE))
                .buildUpon()
                .appendQueryParameter("sort_by", getSortByValue(sortBy))
                .appendQueryParameter("api_key", propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Discover movies Uri: " + discoverMoviesUri.toString());
        return discoverMoviesUri;
    }

    public Uri getMoviePoster185Uri(String imageUri) {

        Uri moviePosterUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_POSTER))
                .buildUpon()
                .appendPath(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_POSTER_W185))
                .appendPath(removeSlash(imageUri))
                .build();

        Log.v(LOG_TAG, "Movie poster Uri: " + moviePosterUri.toString());
        return moviePosterUri;
    }

    public Uri getMoviePoster780Uri(String imageUri) {

        Uri moviePosterUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_POSTER))
                .buildUpon()
                .appendPath(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_POSTER_W780))
                .appendPath(removeSlash(imageUri))
                .build();

        Log.v(LOG_TAG, "Movie poster Uri: " + moviePosterUri.toString());
        return moviePosterUri;
    }

    public Uri getMovieDetails(int movieId) {
        Uri movieDetailsUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_DETAILS))
                .buildUpon()
                .appendPath(String.valueOf(movieId))
                .appendQueryParameter("api_key", propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Movie details Uri: " + movieDetailsUri.toString());
        return movieDetailsUri;
    }

    public Uri getTrailer(int movieId) {
        Uri movieTrailerUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_DETAILS))
                .buildUpon()
                .appendPath(String.valueOf(movieId))
                .appendPath("videos")
                .appendQueryParameter("api_key", propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Movie trailer Uri: " + movieTrailerUri.toString());
        return movieTrailerUri;
    }

    public Uri getReviews(int movieId) {
        Uri movieReviewsUri = Uri.parse(propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_MOVIE_DETAILS))
                .buildUpon()
                .appendPath(String.valueOf(movieId))
                .appendPath("reviews")
                .appendQueryParameter("api_key", propertyReader_.getValue(MoviedroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Movie reviews Uri: " + movieReviewsUri.toString());
        return movieReviewsUri;
    }

    private String getSortByValue(SortBy sortBy) {

        if (SortBy.MOST_POPULAR == sortBy) {
            return "popularity.desc";
        }

        if (SortBy.HIGHEST_RATED == sortBy) {
            return "vote_average.desc";
        }

        return "popularity.desc";
    }

    private String removeSlash(String value) {
        if (value == null) {
            return "";
        }

        if (value.startsWith("/")) {
            value = value.substring(value.indexOf("/") + 1);
        }

        return value;
    }
}
