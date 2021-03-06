package com.cloudskol.moviedroid.list;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.details.DetailActivity;
import com.cloudskol.moviedroid.favorite.FavoritesTask;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.model.SortBy;
import com.cloudskol.moviedroid.provider.MovieDbHelper;
import com.cloudskol.moviedroid.provider.MovieProvider;
import com.cloudskol.moviedroid.settings.MovieSettingsActivity;

import java.util.ArrayList;

/**
 * @author tham
 *
 * Fragment implementation for movie list
 * This fragment will be shown by defalt when the app starts
 */
public class MoviesFragment extends Fragment {
    private MoviesGridAdapter moviesGridAdapter;
    private MoviedroidPropertyReader moviedroidPropertyReader;
    private MoviedroidUriBuilder moviedroidUriBuilder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviedroidPropertyReader = MoviedroidPropertyReader.getInstance(getActivity());
        moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

//        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_spotify, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        final int itemId = item.getItemId();
//        if (itemId == R.id.action_refresh) {
//            loadMoviesData();
//            return true;
//        }
//
//        if (itemId == R.id.action_settings) {
//            showSettings();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void showSettings() {
        final Intent movieSettingsIntent = new Intent(getActivity(), MovieSettingsActivity.class);
        startActivity(movieSettingsIntent);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadMoviesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_list_activity, container, false);

        initCustomGridView(rootView);
        return rootView;
    }

    private void initCustomGridView(View rootView) {
        final GridView spotifyGridView = (GridView) rootView.findViewById(R.id.gridView_spotify);
        moviesGridAdapter = new MoviesGridAdapter(getActivity(), moviedroidUriBuilder, new ArrayList<Movie>(2));
        spotifyGridView.setAdapter(moviesGridAdapter);

        spotifyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MovieSelectionCallback)getActivity()).onMovieSelected(moviesGridAdapter.getItem(position).getId());
//                final Intent movieDetailsIntent = new Intent(getActivity(),
//                        DetailActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT, moviesGridAdapter.getItem(position).getId());
//                startActivity(movieDetailsIntent);
            }
        });

        loadMoviesData();
    }

    private void loadMoviesData() {
        final SortBy sortByPreferenceValue = getSortByPreferenceValue();
        if (SortBy.FAVORITE == sortByPreferenceValue) {
            final FavoritesTask favoritesTask = new FavoritesTask(moviesGridAdapter, getContext());
            favoritesTask.execute();
        } else {
            final MoviesAsyncTask moviesAsyncTask = new MoviesAsyncTask(moviesGridAdapter);
            moviesAsyncTask.execute(moviedroidUriBuilder.discoverMoviesUri(sortByPreferenceValue));
        }
    }

    /**
     * Reads the share preference configuration for sort by value
     *
     * @return SortBy enum value
     */
    private SortBy getSortByPreferenceValue() {
        final String sortByValue = PreferenceManager.getDefaultSharedPreferences(
                getActivity()).getString(getString(R.string.pref_sort_by_key),
                getString(R.string.pref_sort_by_default_value));
        return SortBy.get(Integer.valueOf(sortByValue));
    }
}
