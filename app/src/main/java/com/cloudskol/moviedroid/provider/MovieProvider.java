package com.cloudskol.moviedroid.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author tham
 */
public class MovieProvider extends ContentProvider {
    private static final String LOG_TAG = MovieProvider.class.getSimpleName();

    private static final int MOVIES = 1;
    private static final int MOVIE_BY_ID = 2;

    private static final String CONTENT_AUTHORITY = "com.cloudskol.moviedroid";
    private static final String MOVIES_URI = "content://com.cloudskol.moviedroid/movies";
    public static final Uri CONTENT_URI = Uri.parse(MOVIES_URI);

    private static final String PATH_MOVIES = "movies";
    private static final String PATH_MOVIES_BY_ID = "movies/#";

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private SQLiteDatabase readableDb;
    private SQLiteDatabase writableDb;

    @Override
    public boolean onCreate() {
        MovieDbHelper dbHelper = new MovieDbHelper(getContext());
        readableDb = dbHelper.getReadableDatabase();
        writableDb = dbHelper.getWritableDatabase();
        return (readableDb != null) && (writableDb != null);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.v(LOG_TAG, "Query uri: " + uri.toString());
        Cursor cursor = null;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                cursor = readableDb.query(MovieContract.MovieEntry.TABLE_NAME, null, null, null, null, null, null);
                cursor.setNotificationUri(getContext().getContentResolver(), uri); //Registers the content resolvers to receive notification
                break;
            case MOVIE_BY_ID:
                break;
            default:
                break;
        }

        return cursor;
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

    @Nullable
    @Override
    public String getType(Uri uri) {
        String typeValue;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case MOVIES:
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

    static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_MOVIES, MOVIES);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_MOVIES_BY_ID, MOVIE_BY_ID);

        return uriMatcher;
    }
}
