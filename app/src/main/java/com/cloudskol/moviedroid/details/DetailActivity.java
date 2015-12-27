package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cloudskol.moviedroid.R;

/**
 * @author tham
 *
 * Activity responsible for showing the details of the selected movie
 * This will internally use DetailFragment for rendering the details of the selected movie
 *
 */
public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    private DetailFragment detailFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        if (savedInstanceState == null) {
            final int movieId = getIntent().getIntExtra(Intent.EXTRA_TEXT, -1);
            final Bundle bundle = new Bundle();
            bundle.putInt(Intent.EXTRA_TEXT, movieId);
            detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, detailFragment)
                    .commit();
        }
    }

    public void  onPlayTrailer(View view) {
        detailFragment.onPlayTrailer(view);
    }

    public void onReview(View view) {
        detailFragment.onReview(view);
    }

    public void onShare(View view) {
        detailFragment.onShare(view);
    }
}
