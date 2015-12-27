package com.cloudskol.moviedroid.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cloudskol.moviedroid.R;
import com.cloudskol.moviedroid.common.MoviedroidPropertyReader;
import com.cloudskol.moviedroid.common.MoviedroidUriBuilder;
import com.cloudskol.moviedroid.model.Trailer;
import com.cloudskol.moviedroid.model.Video;

/**
 * @author tham
 */
public class TrailerActivity extends AppCompatActivity {
    private static final String LOG_TAG = TrailerActivity.class.getSimpleName();

    private TrailerListAdapter trailerListAdapter;
    MoviedroidPropertyReader moviedroidPropertyReader;
    MoviedroidUriBuilder moviedroidUriBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trailer_content);

        moviedroidPropertyReader = MoviedroidPropertyReader.getInstance(this);
        moviedroidUriBuilder = new MoviedroidUriBuilder(moviedroidPropertyReader);

        renderTrailer(getIntent().getIntExtra(Intent.EXTRA_TEXT, -1));
    }

    private void renderTrailer(int movieId) {
        trailerListAdapter = new TrailerListAdapter(this, new Trailer());
        final ListView trailerListView = (ListView)findViewById(R.id.trailer_listview);
        trailerListView.setAdapter(trailerListAdapter);

        //Adding click event for the list view
        trailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Video video = trailerListAdapter.getItem(position);
                watchYoutubeVideo(video.getKey());
            }
        });

        final TrailerTask trailerTask = new TrailerTask(this);
        trailerTask.execute(moviedroidUriBuilder.getTrailer(movieId));
    }

    private void watchYoutubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(LOG_TAG, "Error while playing video with Youtube", e);
            Intent intent=new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v="+id));
            startActivity(intent);
        }
    }

    public void onTrailerDataReceived(Trailer trailer) {
        trailerListAdapter.setTrailer(trailer);
    }
}
