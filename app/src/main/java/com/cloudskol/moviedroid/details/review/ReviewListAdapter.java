package com.cloudskol.moviedroid.details.review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cloudskol.moviedroid.model.Review;

import java.util.List;

/**
 * @author tham
 */
public class ReviewListAdapter extends BaseAdapter {
    private static final String LOG_TAG = ReviewListAdapter.class.getSimpleName();

    private List<Review> reviews;
    final LayoutInflater layoutInflater;

    public ReviewListAdapter(ReviewActivity reviewActivity, List<Review> reviews) {
        this.reviews = reviews;
        layoutInflater = reviewActivity.getLayoutInflater();
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

    public void setReviews(List<Review> reviews) {
        notifyDataSetChanged();
    }
}
