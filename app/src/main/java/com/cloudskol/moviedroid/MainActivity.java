package com.cloudskol.moviedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cloudskol.moviedroid.details.DetailFragment;
import com.cloudskol.moviedroid.list.MoviesFragment;

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

    private void renderDetailLayout(Bundle savedInstanceState) {
        hasDetailLayout_ = true;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_details_container, new DetailFragment())
                    .commit();
        }
    }
}
