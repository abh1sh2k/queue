package com.abhishek.queue;

import com.example.queue.Message;

public class Consumer {
    public String id = "";
    String topic = "";

    Consumer(String id) {
        this.id = id;
    }

    public void recieve(Message message) {
        System.out.println("recieved message {" + message + "} at consumerid {" + id + "} at time " + System.currentTimeMillis());
    }
}
