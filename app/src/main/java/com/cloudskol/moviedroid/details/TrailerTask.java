package com.cloudskol.moviedroid.details;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;
import com.cloudskol.moviedroid.common.MoviedroidUrlConnector;
import com.cloudskol.moviedroid.model.Trailer;
import com.cloudskol.moviedroid.parser.TrailerJsonParser;

/**
 * @author tham
 *
 * Async task for fetching trailer for the given movie
 */
public class TrailerTask extends AsyncTask<Uri, Void, Trailer> {
    private static final String LOG_TAG = TrailerTask.class.getSimpleName();

    private TrailerActivity trailerActivity_;

    public TrailerTask(TrailerActivity trailerActivity) {
        trailerActivity_ = trailerActivity;
    }

    @Override
    protected Trailer doInBackground(Uri... params) {
        if (params == null) {
            return null;
        }

        String trailerJson;
        Uri trailerUri = params[0];
        final MoviedroidUrlConnector moviedroidUrlConnector = new MoviedroidUrlConnector();
        try {
            trailerJson = moviedroidUrlConnector.getJson(trailerUri);
        } catch (MoviedroidException e) {
            Log.e(LOG_TAG, "Error while fetching trailer JSON", e);
            return null;
        }

        Log.v(LOG_TAG, "Trailer Json: " + trailerJson);
        return TrailerJsonParser.getInstance().getTrailer(trailerJson);
    }

    @Override
    protected void onPostExecute(Trailer trailer) {
        super.onPostExecute(trailer);
        trailerActivity_.onTrailerDataReceived(trailer);
        Log.v(LOG_TAG, "Trailer received " + trailer);
    }
}
