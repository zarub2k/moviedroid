package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.favorite.FavoriteTask;
import com.cloudskol.moviedroid.review.ReviewActivity;
import com.cloudskol.moviedroid.trailer.TrailerActivity;
import com.cloudskol.moviedroid.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * @author tham
 *
 * Fragment implementation for showing movie details
 */
public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    private ShareActionProvider shareActionProvider;
    final MoviedroidPropertyReader moviedroidPropertyReader;
    final MoviedroidUriBuilder moviedroidUriBuilder;
    private Movie currentMovie_;

    private ImageView posterImage;
    private TextView title;
    private TextView overview;
    private RatingBar ratingBar;
    private TextView releaseDateView;

    public DetailFragment() {
        moviedroidPropertyReader = MoviedroidPropertyReader.getInstance(getActivity());
        moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        setHasOptionsMenu(true);
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

//        final int movieId = getActivity().getIntent().getIntExtra(Intent.EXTRA_TEXT, -1);
//        renderMovieDetails(movieId);
        renderMovieDetails(getCurrentMovieId());

//        final View trailerView = rootView.findViewById(R.id.trailer_listview);
//        renderTrailer(rootView, movieId);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);

        MenuItem shareMenu = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenu);

        final Intent shareIntent = new Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, "Watch this movie!");
        shareIntent.setType("text/plain");
        setShareIntent(shareIntent);
    }

    private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_trailer:
                onPlayTrailer();
                return true;

            case R.id.action_favorite:
                onFavorite();
                return true;

            case R.id.action_review:
                onReview();
                return true;

            case R.id.action_share:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int getCurrentMovieId() {
        final Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getInt(Intent.EXTRA_TEXT);
        }

        return -1;
    }

    public void onPlayTrailer() {
        final Intent trailerIntent = new Intent(getActivity(), TrailerActivity.class)
                .putExtra(Intent.EXTRA_TEXT, getCurrentMovieId());
        startActivity(trailerIntent);
    }

    public void onReview() {
        final Intent trailerIntent = new Intent(getActivity(), ReviewActivity.class)
                .putExtra(Intent.EXTRA_TEXT, getCurrentMovieId());
        startActivity(trailerIntent);
    }

    public void onFavorite() {
        final FavoriteTask favoriteTask = new FavoriteTask(getActivity());
        favoriteTask.execute(currentMovie_);
    }

    private void renderMovieDetails(int movieId) {
        MovieDetailsAsyncTask movieDetailsTask = new MovieDetailsAsyncTask(this);
        movieDetailsTask.execute(moviedroidUriBuilder.getMovieDetails(movieId));
    }

    public void onMovieDataReceived(Movie movie) {
        currentMovie_ = movie;
        if (movie == null) {
            return;
        }

        Picasso.with(getActivity()).load(moviedroidUriBuilder.getMoviePoster780Uri(movie.getBackdrop())).into(posterImage);
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        ratingBar.setRating(movie.getRating());
        releaseDateView.setText(movie.getReleaseDate());
    }
}
