package com.cloudskol.moviedroid.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 */
public class ReviewListAdapter extends BaseAdapter {
    private static final String LOG_TAG = ReviewListAdapter.class.getSimpleName();

    private List<Review> reviews_;
    final LayoutInflater layoutInflater;

    public ReviewListAdapter(ReviewActivity reviewActivity, List<Review> reviews) {
        layoutInflater = (LayoutInflater) reviewActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initializeReviews(reviews);
    }

    @Override
    public int getCount() {
        return reviews_.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews_.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReviewViewHolder holder = null;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.review_list_item, null, false);

            holder = new ReviewViewHolder();
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ReviewViewHolder) convertView.getTag();
        }

        final Review review = reviews_.get(position);
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

        return convertView;
    }

    public void setReviews(List<Review> reviews) {
        if (isReviewsEmpty(reviews)) {
            return;
        }

        reviews_.addAll(reviews);
        notifyDataSetChanged();
    }

    private class ReviewViewHolder {
        TextView author;
        TextView content;
    }

    private void initializeReviews(List<Review> reviews) {
        if (reviews_ == null) {
            reviews_ = new ArrayList<Review>(2);
        }

        if (isReviewsEmpty(reviews)) {
            return;
        }

        reviews_.addAll(reviews);

    }

    private boolean isReviewsEmpty(List<Review> reviews) {
        return reviews == null || reviews.isEmpty();
    }
}
