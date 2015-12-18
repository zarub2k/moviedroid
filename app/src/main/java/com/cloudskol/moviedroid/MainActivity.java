package com.cloudskol.moviedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.cloudskol.moviedroid.details.DetailFragment;
import com.cloudskol.moviedroid.settings.MovieSettingsActivity;

/**
 * @author tham
 *
 * Main activity for the moviedroid application
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private boolean hasDetailLayout_ = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Detail container is enabled
        if (findViewById(R.id.movie_details_container) != null) {
            renderDetailLayout(savedInstanceState);
        } else {
            hasDetailLayout_ = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spotify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
//        if (itemId == R.id.action_refresh) {
//            loadMoviesData();
//            return true;
//        }

        if (itemId == R.id.action_settings) {
            showSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
