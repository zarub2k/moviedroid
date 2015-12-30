package com.cloudskol.moviedroid.review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Review;

import java.util.List;

/**
 * @author tham
 *
 * Activity to render review of the given movie
 */
public class ReviewActivity extends AppCompatActivity {
    private static final String LOG_TAG = ReviewActivity.class.getSimpleName();

    private ReviewListAdapter reviewListAdapter;
    private MoviedroidPropertyReader moviedroidPropertyReader;
    private MoviedroidUriBuilder moviedroidUriBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_content);

        moviedroidPropertyReader = MoviedroidPropertyReader.getInstance(this);
        moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        renderReview(getIntent().getIntExtra(Intent.EXTRA_TEXT, -1));
    }

    private void renderReview(int movieId) {
        reviewListAdapter = new ReviewListAdapter(this, null);
        ListView reviewListView = (ListView) findViewById(R.id.review_listview);
        reviewListView.setAdapter(reviewListAdapter);

        final ReviewTask reviewTask = new ReviewTask(this);
        reviewTask.execute(moviedroidUriBuilder.getReviews(movieId));
    }

    public void onReviewDataReceived(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            this.finish();
            Toast.makeText(ReviewActivity.this, "No review(s) available", Toast.LENGTH_SHORT).show();
            return;
        }

        reviewListAdapter.setReviews(reviews);
    }
}
