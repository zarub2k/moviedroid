package com.cloudskol.cloudroid.spotify;

import android.net.Uri;
import android.util.Log;

import com.cloudskol.cloudroid.common.CloudroidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author tham
 */
public class SpotifyUrlConnector {
    public String getJson(Uri uri) throws CloudroidException {
        return getJson(uri.toString());
    }

    public String getJson(String path) throws CloudroidException {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        try {
            final URL url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line + "\n");
            }

            if (jsonStringBuilder.length() == 0) {
                return null;
            }

            return jsonStringBuilder.toString();
        } catch (Exception e) {
            throw new CloudroidException(e);
        } finally {
            cleanUpResource(urlConnection, bufferedReader);
        }
    }

    /**
     * Method to clean the resources after the content has been read
     *
     * @param urlConnection
     * @param bufferedReader
     */
    private void cleanUpResource(HttpURLConnection urlConnection, BufferedReader bufferedReader) {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (final IOException e) {
                Log.e("SpotifyUrlConnector", "Error while closing the buffered reader", e);
            }
        }
    }
}
