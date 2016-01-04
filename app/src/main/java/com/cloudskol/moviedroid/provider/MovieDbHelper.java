package com.cloudskol.moviedroid.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author tham
 */
public class MovieDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = MovieDbHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Moviedroid.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String buildCreateTableQuery() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        builder.append(MovieContract.MovieEntry.TABLE_NAME);

        Log.v(LOG_TAG, "Create table query: " + builder.toString());
        return builder.toString();
    }

    private String buildDropTableQuery() {
        return "DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;
    }
}
