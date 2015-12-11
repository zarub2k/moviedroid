package com.cloudskol.cloudroid.spotify;

import android.net.Uri;
import android.util.Log;

import com.cloudskol.cloudroid.common.CloudroidPropertyKeys;
import com.cloudskol.cloudroid.common.CloudroidPropertyReader;

/**
 * @author tham
 *
 * Utility class for building Uri for list of movies and movie details
 */
public class SpotifyUriBuilder {

    private static final String LOG_TAG = SpotifyUriBuilder.class.getSimpleName();

    private CloudroidPropertyReader propertyReader_;

    public SpotifyUriBuilder(CloudroidPropertyReader propertyReader) {
        propertyReader_ = propertyReader;
    }

    public Uri discoverMoviesUri(SortBy sortBy) {
        Uri discoverMoviesUri = Uri.parse(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_DISCOVER_MOVIE))
                .buildUpon()
                .appendQueryParameter("sort_by", getSortByValue(sortBy))
                .appendQueryParameter("api_key", propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Discover movies Uri: " + discoverMoviesUri.toString());
        return discoverMoviesUri;
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

    public Uri getMoviePoster185Uri(String imageUri) {

        Uri moviePosterUri = Uri.parse(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_MOVIE_POSTER))
                .buildUpon()
                .appendPath(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_MOVIE_POSTER_W185))
                .appendPath(removeSlash(imageUri))
                .build();

        Log.v(LOG_TAG, "Movie poster Uri: " + moviePosterUri.toString());
        return moviePosterUri;
    }

    public Uri getMoviePoster780Uri(String imageUri) {

        Uri moviePosterUri = Uri.parse(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_MOVIE_POSTER))
                .buildUpon()
                .appendPath(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_MOVIE_POSTER_W780))
                .appendPath(removeSlash(imageUri))
                .build();

        Log.v(LOG_TAG, "Movie poster Uri: " + moviePosterUri.toString());
        return moviePosterUri;
    }

    public Uri getMovieDetails(int movieId) {
        Uri movieDetailsUri = Uri.parse(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_MOVIE_DETAILS))
                .buildUpon()
                .appendPath(String.valueOf(movieId))
                .appendQueryParameter("api_key", propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Movie details Uri: " + movieDetailsUri.toString());
        return movieDetailsUri;
    }

    private String removeSlash(String value) {
        if (value.startsWith("/")) {
            value = value.substring(value.indexOf("/") + 1);
        }

        return value;
    }
}
