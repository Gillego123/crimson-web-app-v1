package com.crimson.app.crimson.model;

import java.sql.Timestamp;

public class Messages {

    private Long messagesId;

    private User user;

    private User receiver;

    private Complaint complaint;

    private String message;

    private Timestamp sentAt;
}
