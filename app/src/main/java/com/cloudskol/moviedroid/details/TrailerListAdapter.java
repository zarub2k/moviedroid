package com.cloudskol.moviedroid.details;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.model.Trailer;
import com.cloudskol.moviedroid.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 *
 * Adapter implementation for Trailers
 */
public class TrailerListAdapter extends BaseAdapter {
    private static final String LOG_TAG = TrailerListAdapter.class.getSimpleName();

    Context context_;
    LayoutInflater layoutInflater_;

    private Trailer trailer_;
    private List<Video> videos;

    public TrailerListAdapter(Context context, Trailer trailer) {
        context_ = context;
        trailer_ = trailer;
        layoutInflater_ = (LayoutInflater) context_.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (trailer_.getVideos() != null) {
            videos = new ArrayList<Video>(trailer_.getVideos());
        }
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Video getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TrailerViewHolder holder = null;

        Log.v(LOG_TAG, "View: " + view.toString());

        if (view == null) {
            view = layoutInflater_.inflate(R.layout.trailer_list_item, parent, false);
            holder = new TrailerViewHolder();
            holder.trailerNameView = (TextView) view.findViewById(R.id.trailer_name);
            view.setTag(holder);
        } else {
            holder = (TrailerViewHolder) view.getTag();
        }

        final Video video = videos.get(position);
        holder.trailerNameView.setText(video.getName());

        Log.v(LOG_TAG, "Video name: " + video.getName());

        return view;
    }

    private class TrailerViewHolder {
        TextView trailerNameView;
    }

    public void setTrailer(Trailer trailer) {
        trailer_ = trailer;
        videos.clear();
        videos.addAll(trailer.getVideos());

        notifyDataSetChanged();
    }
}
