package com.cloudskol.moviedroid.details;

import android.net.Uri;
import android.os.AsyncTask;

import com.cloudskol.moviedroid.model.Trailer;

/**
 * @author tham
 */
public class TrailerTask extends AsyncTask<Uri, Void, Trailer> {
    private DetailFragment detailFragment_;;

    public TrailerTask(DetailFragment detailFragment) {
        detailFragment_ = detailFragment;
    }

    @Override
    protected Trailer doInBackground(Uri... params) {
        if (params == null) {
            return null;
        }

        Uri trailerUri = params[0];
        return null;
    }
}
