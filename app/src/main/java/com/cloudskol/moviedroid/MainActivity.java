package com.cloudskol.moviedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cloudskol.moviedroid.list.MoviesFragment;

/**
 * @author tham
 *
 * Main activity for the moviedroid application
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
