package com.cloudskol.moviedroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cloudskol.moviedroid.details.DetailActivity;
import com.cloudskol.moviedroid.details.DetailFragment;
import com.cloudskol.moviedroid.favorite.FavoritesActivity;
import com.cloudskol.moviedroid.list.MovieSelectionCallback;
import com.cloudskol.moviedroid.settings.MovieSettingsActivity;

/**
 * @author tham
 *
 * Main activity for the moviedroid application
 */
public class MainActivity extends AppCompatActivity implements MovieSelectionCallback {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private boolean hasDetailLayout_ = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Detail container is enabled
        if (findViewById(R.id.movie_details_container) != null) {
            hasDetailLayout_ = true;
//            renderDetailLayout(savedInstanceState);
        } else {
            hasDetailLayout_ = false;
        }

        //Enable action bar
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.action_refresh) {
//            loadMoviesData();
            return true;
        }

        if (itemId == R.id.action_settings) {
            showSettings();
            return true;
        }

        if (itemId == R.id.action_favorite) {
            showFavorites();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFavorites() {
        final Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    private void showSettings() {
        final Intent movieSettingsIntent = new Intent(this, MovieSettingsActivity.class);
        startActivity(movieSettingsIntent);
    }

    private void renderDetailLayout(Bundle savedInstanceState) {
        hasDetailLayout_ = true;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, new DetailFragment())
                    .commit();
        }
    }

    @Override
    public void onMovieSelected(int movieId) {
        if (hasDetailLayout_) {
            final Bundle bundle = new Bundle();
            bundle.putInt(Intent.EXTRA_TEXT, movieId);
            final DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, detailFragment)
                    .commit();
        } else {
            final Intent movieDetailsIntent = new Intent(this, DetailActivity.class)
                    .putExtra(Intent.EXTRA_TEXT, movieId);
            startActivity(movieDetailsIntent);
        }
    }
}
