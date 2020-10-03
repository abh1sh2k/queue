package com.abhishek.queue;

import com.example.queue.Message;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Broker {
    private Queue queue;

    @Inject
    Broker(Queue queue) {
        this.queue = queue;
    }

    public void publishMessage(Message message) {
        queue.addMessage(message);
    }

    public Message consume() {
        Object o = queue.blockingDeque.pollFirst();
        if (o instanceof Message)
            return (Message) o;
        else
            return null;
    }
}
