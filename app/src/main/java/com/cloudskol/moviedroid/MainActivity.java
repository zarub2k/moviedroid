package com.cloudskol.moviedroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cloudskol.moviedroid.scores.ScoresActivity;
import com.cloudskol.moviedroid.movies.SpotifyActivity;

/**
 * Main entry point for the application
 *
 * @author tham
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSpotify(View view) {
        final Intent spotifyIntent = new Intent(this, SpotifyActivity.class);
        startActivity(spotifyIntent);
    }

    public void onScores(View view) {
        final Intent scoresIntent = new Intent(this, ScoresActivity.class);
        startActivity(scoresIntent);
    }

    public void onLibrary(View view) {
        showToast(MainActivity.this, "Library");
    }

    public void onBigger(View view) {
        showToast(MainActivity.this, "Bigger");
    }

    public void onReader(View view) {
        showToast(MainActivity.this, "Reader");
    }

    public void onCustom(View view) {
        showToast(MainActivity.this, "Custom");
    }

    private void showToast(Context context, String appName) {
        Toast.makeText(context, "This button will launch " + appName + " app!", Toast.LENGTH_SHORT).show();
    }
}
