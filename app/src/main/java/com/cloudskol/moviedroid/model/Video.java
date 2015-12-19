package com.cloudskol.moviedroid.model;

/**
 * @author tham
 */
public class Video {
    private String name;
    private String key;

    public Video() {}

    public Video(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}