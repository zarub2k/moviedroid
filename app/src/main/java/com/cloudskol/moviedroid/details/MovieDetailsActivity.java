package com.cloudskol.moviedroid.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
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

        final int movieId = getIntent().getIntExtra(Intent.EXTRA_TEXT, -1);
        renderMovieDetails(movieId);

//        TextView textView = (TextView) findViewById(R.id.movie_details_textview);
//        textView.setText(movieTitle);
    }

    private void renderMovieDetails(int movieId) {
        final MoviedroidPropertyReader moviedroidPropertyReader = MoviedroidPropertyReader
                .getInstance(getBaseContext());
        final MoviedroidUriBuilder moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        MovieDetailsAsyncTask movieDetailsTask = new MovieDetailsAsyncTask(this);
        movieDetailsTask.execute(moviedroidUriBuilder.getMovieDetails(movieId));
    }

    public void onMovieDataReceived(Movie movie) {
        final MoviedroidPropertyReader moviedroidPropertyReader = MoviedroidPropertyReader
                .getInstance(getBaseContext());
        final MoviedroidUriBuilder moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        final ImageView posterImage = (ImageView) findViewById(R.id.movie_details_poster);
        Picasso.with(this).load(moviedroidUriBuilder.getMoviePoster780Uri(movie.getBackdrop())).into(posterImage);

        TextView title = (TextView) findViewById(R.id.movie_details_title);
        title.setText(movie.getTitle());

        TextView textView = (TextView) findViewById(R.id.movie_details_overview);
        textView.setText(movie.getOverview());

        RatingBar ratingBar = (RatingBar) findViewById(R.id.movie_details_rating);
        ratingBar.setRating(movie.getRating());

        TextView releaseDateView = (TextView) findViewById(R.id.movie_details_release_date);
        releaseDateView.setText(movie.getReleaseDate());
    }
}
