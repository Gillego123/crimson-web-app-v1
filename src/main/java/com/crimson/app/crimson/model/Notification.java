package com.crimson.app.crimson.model;

import java.sql.Timestamp;

public class Notification {

    private Long notificationId;

    private User user;

    private String message;
    private Boolean isRead= false;
    private Timestamp createAt;


}
