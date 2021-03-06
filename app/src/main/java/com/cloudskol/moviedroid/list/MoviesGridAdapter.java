package com.cloudskol.moviedroid.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author tham
 *
 * Adapter class for rendering list of movies
 */
public class MoviesGridAdapter extends BaseAdapter {
    private Context context_;
    private MoviedroidUriBuilder moviedroidUriBuilder_;
    private List<Movie> movies_;

    public MoviesGridAdapter(Context context, MoviedroidUriBuilder moviedroidUriBuilder,
                             List<Movie> movies) {
        context_ = context;
        moviedroidUriBuilder_ = moviedroidUriBuilder;
        movies_ = movies;
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
        ImageView imageView;
        if (convertView == null) {
            imageView =  new ImageView(context_);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setMinimumWidth(100);
        imageView.setMinimumHeight(250);

        final Movie movie = movies_.get(position);
        Picasso.with(context_)
                .load(moviedroidUriBuilder_.getMoviePoster185Uri(movie.getPoster()))
                .placeholder(R.drawable.ic_sync_black_24dp)
                .error(R.drawable.ic_action)
                .into(imageView);
        imageView.setContentDescription(movie.getOriginalTitle());
        return imageView;
    }

    public void addAll(List<Movie> movies) {
        movies_.addAll(movies);
        notifyDataSetChanged();
    }

    public void clear() {
        movies_.clear();
        notifyDataSetChanged();
    }
}
