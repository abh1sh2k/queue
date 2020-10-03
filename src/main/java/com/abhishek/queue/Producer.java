package com.abhishek.queue;

import com.example.queue.Message;
import com.google.inject.Inject;


public class Producer extends Thread {

    private final Broker broker;

    @Inject
    Producer(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void run() {
        while (true) {
            Message message = MessageCreater.getMessage();
            try {
                sleep(1000);
            } catch (Exception ex) {

            }
            broker.publishMessage(message);
        }
    }
}
