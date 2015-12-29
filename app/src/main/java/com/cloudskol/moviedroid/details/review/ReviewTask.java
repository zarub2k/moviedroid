package com.cloudskol.moviedroid.details.review;

import android.net.Uri;
import android.os.AsyncTask;

import com.cloudskol.moviedroid.model.Review;

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
        return null;
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        reviewActivity_.onReviewDataReceived(reviews);
    }
}
