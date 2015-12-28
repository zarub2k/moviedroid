package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

        //Enable action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_trailer:
                detailFragment.onPlayTrailer(null);
                return true;

            case R.id.action_favorite:
                return true;

            case R.id.action_share:
                return true;

            case R.id.action_review:
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
