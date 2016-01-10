package com.cloudskol.moviedroid.favorite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.model.Movie;
import com.cloudskol.moviedroid.provider.MovieDbHelper;

import java.util.List;

/**
 * @author tham
 */
public class FavoritesActivity extends AppCompatActivity {

    private static final String LOG_TAG = FavoritesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_content);

        renderMyFavorites();
    }

    private void renderMyFavorites() {
        final MovieDbHelper movieDbHelper = new MovieDbHelper(this);
        final FavoritesTask favoritesTask = new FavoritesTask(this, movieDbHelper);
        favoritesTask.execute();
    }

    public void onFavoriteMoviesReceived(List<Movie> movies) {
        
    }
}
