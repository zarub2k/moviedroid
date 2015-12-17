package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * @author tham
 */
public class DetailFragment extends Fragment {
    final MoviedroidPropertyReader moviedroidPropertyReader;
    final MoviedroidUriBuilder moviedroidUriBuilder;

    private ImageView posterImage;
    private TextView title;
    private TextView overview;
    private RatingBar ratingBar;
    private TextView releaseDateView;

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
        return rootView;
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
}
