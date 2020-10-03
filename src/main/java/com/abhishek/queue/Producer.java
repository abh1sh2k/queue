package com.abhishek.queue;

import com.example.queue.Message;


public class Producer extends Thread {

    private final Broker broker;

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
