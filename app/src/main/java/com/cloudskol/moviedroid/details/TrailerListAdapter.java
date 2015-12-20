package com.cloudskol.moviedroid.details;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.model.Trailer;

import java.util.List;

/**
 * @author tham
 */
public class TrailerListAdapter extends BaseAdapter {
    Context context_;
    LayoutInflater layoutInflater_;

    private List<Trailer> trailers;

    public TrailerListAdapter(Context context, List<Trailer> trailers) {
        context_ = context;
        this.trailers = trailers;
        layoutInflater_ = (LayoutInflater) context_.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int position) {
        return trailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater_.inflate(R.layout.trailer_list_item, parent);
            view.findViewById(R.id.trailer_name);
        }

        final Trailer trailer = trailers.get(position);


        return null;
    }

    private class TrailerViewHolder {
        TextView trailerNameView;
    }
}
