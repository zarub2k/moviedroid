package com.cloudskol.moviedroid.list;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author tham
 */
public class MovieService extends IntentService {
    private static final String LOG_TAG = MovieService.class.getSimpleName();
    private static final String SERVICE_NAME = "MovieService";

    public MovieService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        intent.getIntExtra("", -1);
    }
}
