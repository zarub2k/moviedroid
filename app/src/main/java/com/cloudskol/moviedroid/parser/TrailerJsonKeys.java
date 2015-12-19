package com.cloudskol.moviedroid.parser;

/**
 * @author tham
 */
public enum TrailerJsonKeys {
    ID("id"),
    KEY("key"),
    NAME("name"),
    RESULTS("results");

    private String value_;
    private TrailerJsonKeys(String value) {
        value_ = value;
    }

    public String getValue() {
        return value_;
    }
}
