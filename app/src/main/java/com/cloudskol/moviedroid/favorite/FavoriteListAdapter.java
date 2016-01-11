package com.cloudskol.moviedroid.favorite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class FavoriteListAdapter extends BaseAdapter {
    private static final String LOG_TAG = FavoriteListAdapter.class.getSimpleName();

    private List<Movie> movies_;
    private LayoutInflater inflater;

    public FavoriteListAdapter(FavoritesActivity context, List<Movie> movies) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initializeMovies(movies);
    }

    @Override
    public int getCount() {
        return movies_.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies_.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavoriteViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.favorite_list_item, null, false);

            viewHolder = new FavoriteViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.overview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FavoriteViewHolder) convertView.getTag();
        }

        final Movie movie = movies_.get(position);
        viewHolder.title.setText(movie.getTitle());
        viewHolder.overview.setText(movie.getOverview());

        return convertView;
    }

    public void setMovies(List<Movie> movies) {
        if (isMoviesEmpty(movies)) {
            return;
        }

        movies_.addAll(movies);
        notifyDataSetChanged();
    }

    private class FavoriteViewHolder {
        TextView title;
        TextView overview;
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
