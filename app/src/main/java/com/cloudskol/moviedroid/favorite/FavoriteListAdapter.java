package com.cloudskol.moviedroid.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cloudskol.moviedroid.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class FavoriteListAdapter extends BaseAdapter {
    private FavoritesActivity context_;
    private List<Movie> movies_;
    private LayoutInflater inflater;

    public FavoriteListAdapter(FavoritesActivity context, List<Movie> movies) {
        context_ = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        initializeMovies(movies);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setMovies(List<Movie> movies) {
        if (isMoviesEmpty(movies)) {
            return;
        }

        movies_.addAll(movies);
        notifyDataSetChanged();
    }

    private void initializeMovies(List<Movie> movies) {
        if (movies_ == null) {
            movies_ = new ArrayList<Movie>(2);
        }

        if (isMoviesEmpty(movies)) {
            return;
        }

        movies_.addAll(movies);
    }

    private boolean isMoviesEmpty(List<Movie> movies) {
        return movies == null || movies.isEmpty();
    }
}
