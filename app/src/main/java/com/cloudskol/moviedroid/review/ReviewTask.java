package com.cloudskol.moviedroid.review;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudskol.moviedroid.common.MoviedroidException;
import com.cloudskol.moviedroid.common.MoviedroidUrlConnector;
import com.cloudskol.moviedroid.model.Review;
import com.cloudskol.moviedroid.trailer.TrailerJsonParser;

import java.util.List;

/**
 * @author tham
 */
public class ReviewTask extends AsyncTask<Uri, Void, List<Review>>{
    private static final String LOG_TAG = ReviewTask.class.getSimpleName();

    private ReviewActivity reviewActivity_;

    public ReviewTask(ReviewActivity reviewActivity) {
        reviewActivity_ = reviewActivity;
    }

    @Override
    protected List<Review> doInBackground(Uri... params) {
        if (params == null) {
            return null;
        }

        String reviewsJson;
        Uri reviewsUri = params[0];
        final MoviedroidUrlConnector moviedroidUrlConnector = new MoviedroidUrlConnector();
        try {
            reviewsJson = moviedroidUrlConnector.getJson(reviewsUri);
        } catch (MoviedroidException e) {
            Log.e(LOG_TAG, "Error while fetching reviews JSON", e);
            return null;
        }

        Log.v(LOG_TAG, "Trailer Json: " + reviewsJson);
        return TrailerJsonParser.getInstance().getTrailer(reviewsJson);
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        reviewActivity_.onReviewDataReceived(reviews);
    }
}
