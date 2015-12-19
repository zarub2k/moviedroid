package com.cloudskol.moviedroid.model;

import java.util.List;

/**
 * @author tham
 */
public class Trailer {
    private int movieId;
    private List<Video> videos;

    public Trailer(int movieId, List<Video> videos) {
        this.movieId = movieId;
        this.videos = videos;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
