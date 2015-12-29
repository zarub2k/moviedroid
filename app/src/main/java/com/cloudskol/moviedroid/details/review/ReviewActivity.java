package com.cloudskol.moviedroid.details.review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cloudskol.moviedroid.R;

/**
 * @author tham
 *
 * Activity to render review of the given movie
 */
public class ReviewActivity extends AppCompatActivity {
    private static final String LOG_TAG = ReviewActivity.class.getSimpleName();

    private ReviewListAdapter reviewListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_content);

        renderReview(getIntent().getIntExtra(Intent.EXTRA_TEXT, -1));
    }

    private void renderReview(int movieId) {
        reviewListAdapter = new ReviewListAdapter();
        ListView reviewListView = (ListView) findViewById(R.id.review_listview);
        reviewListView.setAdapter(reviewListAdapter);
    }
}
