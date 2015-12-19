package com.cloudskol.moviedroid.parser;

/**
 * @author tham
 */
public enum MovieJsonKeys {
    BACKDROP("backdrop_path"),
    ID("id"),
    ORIGINAL_TITLE("original_title"),
    OVERVIEW("overview"),
    POSTER("poster_path"),
    RATING("vote_average"),
    RELEASE_DATE("release_date"),
    TITLE("title");

    private String value_;
    private MovieJsonKeys(String value) {
        value_ = value;
    }

    public String getValue() {
        return value_;
    }
}
