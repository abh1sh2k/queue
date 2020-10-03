package com.example.queue;

public class Message {

    public String body;

    private String topic;

    private long timestamp = System.currentTimeMillis();

    private long timeToExpire = -1;

    public Message(String body, String topic, int expiry) {
        this.body = body;
        this.topic = topic;
        this.timeToExpire = timestamp + (long) expiry;
    }


    public String getBody() {
        return body;
    }

    public String getTopic() {
        return topic;
    }

    public long getTimeToExpire() {
        return timeToExpire;
    }
}
