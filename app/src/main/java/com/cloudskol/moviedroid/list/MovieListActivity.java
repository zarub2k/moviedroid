package com.cloudskol.moviedroid.list;

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
import android.widget.GridView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.details.MovieDetailsActivity;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.model.SortBy;
import com.cloudskol.moviedroid.settings.MovieSettingsActivity;

import java.util.ArrayList;

/**
 * @author tham
 *
 * Activity class for spotify application
 */
public class MovieListActivity extends AppCompatActivity {

    private static final String LOG_TAG = MovieListActivity.class.getSimpleName();

    private MoviesGridAdapter moviesGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_activity);
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
        final MoviedroidPropertyReader moviedroidPropertyReader = MoviedroidPropertyReader
                .getInstance(getBaseContext());
        final MoviedroidUriBuilder moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        final GridView spotifyGridView = (GridView) findViewById(R.id.gridView_spotify);
        moviesGridAdapter = new MoviesGridAdapter(this, moviedroidUriBuilder, new ArrayList<Movie>(2));
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
        final MoviedroidPropertyReader moviedroidPropertyReader = MoviedroidPropertyReader
                .getInstance(getBaseContext());
        final MoviedroidUriBuilder moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        final DiscoverMoviesAsyncTask discoverMoviesAsyncTask = new DiscoverMoviesAsyncTask(moviesGridAdapter);
        discoverMoviesAsyncTask.execute(moviedroidUriBuilder.discoverMoviesUri(getSortByPreferenceValue()));
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