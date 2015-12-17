package com.cloudskol.moviedroid.list;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.details.DetailActivity;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.model.SortBy;

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
                final Intent movieDetailsIntent = new Intent(getActivity(),
                        DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, moviesGridAdapter.getItem(position).getId());
                startActivity(movieDetailsIntent);
            }
        });

        loadMoviesData();
    }

    private void loadMoviesData() {
        final MoviesAsyncTask moviesAsyncTask = new MoviesAsyncTask(moviesGridAdapter);
        moviesAsyncTask.execute(moviedroidUriBuilder.discoverMoviesUri(getSortByPreferenceValue()));
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
