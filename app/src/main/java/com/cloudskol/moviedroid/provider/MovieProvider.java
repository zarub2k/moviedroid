package com.cloudskol.moviedroid.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * @author tham
 */
public class MovieProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = buildUriMatcher();

    private static final int MOVIE = 500;
    private static final int MOVIE_BY_ID = 501;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String typeValue;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case MOVIE:
                typeValue = MovieContract.MovieEntry.CONTENT_TYPE;
                break;

            case MOVIE_BY_ID:
                typeValue = MovieContract.MovieEntry.CONTENT_TYPE_ITEM;
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return typeValue;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        final String authority = MovieContract.CONTENT_AUTHORITY;

        uriMatcher.addURI(authority, MovieContract.PATH_MOVIE, MOVIE);
        uriMatcher.addURI(authority, MovieContract.PATH_MOVIE + "/#", MOVIE_BY_ID);

        return uriMatcher;
    }
}
