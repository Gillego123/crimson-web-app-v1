package com.crimson.app.crimson.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ComplaintStatus {
    SUBMITTED,
    UNDER_INVESTIGATION,
    RESOLVED,
    CLOSED,
    CANCELED;

    @JsonCreator
    public static ComplaintStatus fromString(String value) {
        return ComplaintStatus.valueOf(value.toUpperCase());
    }
}
