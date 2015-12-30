package com.cloudskol.moviedroid.review;

import android.util.Log;

import com.cloudskol.moviedroid.model.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class ReviewJsonParser {
    private static final String LOG_TAG = ReviewJsonParser.class.getSimpleName();

    private static final ReviewJsonParser INSTANCE = new ReviewJsonParser();

    private ReviewJsonParser() {}

    public static final synchronized ReviewJsonParser getInstance() {
        return INSTANCE;
    }

    public List<Review> getReviews(String reviewsJsonString) {
        try {
            final JSONObject reviewsJson = new JSONObject(reviewsJsonString);
            final JSONArray results = reviewsJson.getJSONArray("results");
            Log.v(LOG_TAG,  "Size of the result array is : " + results.length());

            return getReviews(results);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing reviews Json", e);
        }

        return null;
    }

    private List<Review> getReviews(JSONArray results) throws JSONException {
        if (results == null || results.length() == 0) {
            return null;
        }

        List<Review> reviews = new ArrayList<>(results.length());
        for (int i = 0; i < results.length(); i++) {
            reviews.add(getReview(results.getJSONObject(i)));
        }

        return reviews;
    }

    private Review getReview(JSONObject reviewJson) throws JSONException {
        return new Review(reviewJson.getString("author"), reviewJson.getString("content"));
    }
}
