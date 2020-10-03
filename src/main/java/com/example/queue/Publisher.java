package com.example.queue;

public class Publisher<M> {
    Broker broker;

    Publisher(Broker broker) {
        this.broker = broker;
    }

    void publish(String topic, String message) {
        broker.publish(topic, message);
    }
}
