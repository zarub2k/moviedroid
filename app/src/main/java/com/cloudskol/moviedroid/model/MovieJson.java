package com.cloudskol.moviedroid.model;

/**
 * @author tham
 */
public enum MovieJson {
    BACKDROP("backdrop_path"),
    ID("id"),
    ORIGINAL_TITLE("original_title"),
    OVERVIEW("overview"),
    POSTER("poster_path"),
    RATING("vote_average"),
    RELEASE_DATE("release_date"),
    TITLE("title");

    private String value_;
    private MovieJson(String value) {
        value_ = value;
    }

    public String getValue() {
        return value_;
    }
}
