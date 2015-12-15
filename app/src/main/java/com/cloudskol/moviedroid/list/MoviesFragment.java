package com.cloudskol.moviedroid.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloudskol.moviedroid.R;

/**
 * @author tham
 *
 * Fragment implementation for movie list
 * This fragment will be shown by defalt when the app starts
 */
public class MoviesFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstance) {
        final View rootView = inflater.inflate(R.layout.movie_list_activity, container, false);
        return rootView;
    }
}
