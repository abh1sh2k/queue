package com.example.queue;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    public String id = "";
    String topic = "";
    List<String> topics = new ArrayList<String>();

    Subscriber(String id) {
        this.id = id;
    }

    public void recieveMessage(String message) {
        System.out.println("recieved message {" + message + "} at consumerid {" + id + "} at time " + System.currentTimeMillis());
    }
}
