package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.model.Trailer;
import com.cloudskol.moviedroid.model.Video;
import com.squareup.picasso.Picasso;

/**
 * @author tham
 *
 * Fragment implementation for showing movie details
 */
public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();


    final MoviedroidPropertyReader moviedroidPropertyReader;
    final MoviedroidUriBuilder moviedroidUriBuilder;

    private ImageView posterImage;
    private TextView title;
    private TextView overview;
    private RatingBar ratingBar;
    private TextView releaseDateView;

    TrailerListAdapter trailerListAdapter;

    public DetailFragment() {
        setHasOptionsMenu(true);
        moviedroidPropertyReader = MoviedroidPropertyReader.getInstance(getActivity());
        moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.detail_fragment, container, false);
        posterImage = (ImageView) rootView.findViewById(R.id.movie_details_poster);
        title = (TextView) rootView.findViewById(R.id.movie_details_title);
        overview = (TextView) rootView.findViewById(R.id.movie_details_overview);
        ratingBar = (RatingBar) rootView.findViewById(R.id.movie_details_rating);
        releaseDateView = (TextView) rootView.findViewById(R.id.movie_details_release_date);

        final int movieId = getActivity().getIntent().getIntExtra(Intent.EXTRA_TEXT, -1);
        renderMovieDetails(movieId);

        final View trailerView = inflater.inflate(R.layout.trailer_content, container);
        renderTrailer(trailerView, movieId);
        return rootView;
    }

    private void renderTrailer(View trailerView, int movieId) {
        final ListView trailerListView = (ListView) trailerView.findViewById(R.id.trailer_listview);

        trailerListAdapter = new TrailerListAdapter(getContext(), new Trailer());
        trailerListView.setAdapter(trailerListAdapter);

        final TrailerTask trailerTask = new TrailerTask(this);
        trailerTask.execute(moviedroidUriBuilder.getTrailer(movieId));
    }

    private void renderMovieDetails(int movieId) {
        MovieDetailsAsyncTask movieDetailsTask = new MovieDetailsAsyncTask(this);
        movieDetailsTask.execute(moviedroidUriBuilder.getMovieDetails(movieId));
    }

    public void onMovieDataReceived(Movie movie) {
        Picasso.with(getActivity()).load(moviedroidUriBuilder.getMoviePoster780Uri(movie.getBackdrop())).into(posterImage);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        ratingBar.setRating(movie.getRating());
        releaseDateView.setText(movie.getReleaseDate());
    }

    public void onTrailerDataReceived(Trailer trailer) {
        trailerListAdapter.setTrailer(trailer);
    }
}
