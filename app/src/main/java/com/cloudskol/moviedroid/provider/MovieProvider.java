package com.cloudskol.moviedroid.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * @author tham
 */
public class MovieProvider extends ContentProvider {
    private static final String LOG_TAG = MovieProvider.class.getSimpleName();

    private static final int ALL_MOVIES = 1;
    private static final int MOVIE_BY_ID = 2;

    private static final String CONTENT_AUTHORITY = "com.cloudskol.moviedroid";
    private static final String MOVIES_URI = "content://com.cloudskol.moviedroid/movies";
    public static final Uri CONTENT_URI = Uri.parse(MOVIES_URI);

    private static final String PATH_MOVIES = "movies";
    private static final String PATH_MOVIES_BY_ID = "movies/#";

    private static final UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        return true;
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
            case ALL_MOVIES:
                typeValue = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "." + PATH_MOVIES;
                break;

            case MOVIE_BY_ID:
                typeValue = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "." + PATH_MOVIES;
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
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_MOVIES, ALL_MOVIES);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_MOVIES_BY_ID, MOVIE_BY_ID);

        return uriMatcher;
    }
}
