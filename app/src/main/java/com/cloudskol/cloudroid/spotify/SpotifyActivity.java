package com.cloudskol.cloudroid.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.cloudskol.cloudroid.R;
import com.cloudskol.cloudroid.common.CloudroidPropertyReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author tham
 *
 * Activity class for spotify application
 */
public class SpotifyActivity extends AppCompatActivity {

    private static final String LOG_TAG = SpotifyActivity.class.getSimpleName();

    private MoviesGridAdapter moviesGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renderMovies();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadMoviesData();
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
        if (itemId == R.id.action_refresh) {
            loadMoviesData();
            return true;
        }

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

    private void renderMovies() {
        initCustomGridView();
//        loadMoviesData();
    }

    private void initCustomGridView() {
        final CloudroidPropertyReader cloudroidPropertyReader = CloudroidPropertyReader
                .getInstance(getBaseContext());
        final SpotifyUriBuilder spotifyUriBuilder = new SpotifyUriBuilder(cloudroidPropertyReader);

        final GridView spotifyGridView = (GridView) findViewById(R.id.gridView_spotify);
        moviesGridAdapter = new MoviesGridAdapter(this, spotifyUriBuilder, new ArrayList<Movie>(2));
        spotifyGridView.setAdapter(moviesGridAdapter);

        spotifyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent movieDetailsIntent = new Intent(getBaseContext(),
                        MovieDetailsActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, moviesGridAdapter.getItem(position).getId());
                startActivity(movieDetailsIntent);
//                Toast.makeText(getBaseContext(), "Grid item: " + arrayAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMoviesData() {
        final CloudroidPropertyReader cloudroidPropertyReader = CloudroidPropertyReader
                .getInstance(getBaseContext());
        final SpotifyUriBuilder spotifyUriBuilder = new SpotifyUriBuilder(cloudroidPropertyReader);

        final DiscoverMoviesAsyncTask discoverMoviesAsyncTask = new DiscoverMoviesAsyncTask(moviesGridAdapter);
        discoverMoviesAsyncTask.execute(spotifyUriBuilder.discoverMoviesUri(getSortByPreferenceValue()));
    }

    /**
     * Reads the share preference configuration for sort by value
     *
     * @return SortBy enum value
     */
    private SortBy getSortByPreferenceValue() {
        final String sortByValue = PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_sort_by_key),
                getString(R.string.pref_sort_by_default_value));
//        Toast.makeText(this, "Sort by value: " + sortByValue, Toast.LENGTH_SHORT).show();
        return SortBy.get(Integer.valueOf(sortByValue));
    }
}