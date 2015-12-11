package com.cloudskol.moviedroid.model;

/**
 * @author tham
 *
 * Enum for providing the sort by functionality for our application
 */
public enum SortBy {
    MOST_POPULAR(0),
    HIGHEST_RATED(1);

    private int value;

    private SortBy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static final SortBy get(int value) {
        SortBy matchedEnum = null;
        for (SortBy sortBy : SortBy.values()) {
            if (sortBy.getValue() == value) {
                matchedEnum = sortBy;
                break;
            }
        }

        return matchedEnum;
    }
}
