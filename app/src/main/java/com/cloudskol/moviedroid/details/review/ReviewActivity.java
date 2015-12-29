package com.cloudskol.moviedroid.details.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cloudskol.moviedroid.R;

/**
 * @author tham
 *
 * Activity to render review of the given movie
 */
public class ReviewActivity extends AppCompatActivity {
    private static final String LOG_TAG = ReviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_content);
    }
}
