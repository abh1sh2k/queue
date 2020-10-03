package com.abhishek.queue;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Singleton
public class Queue<E> {
    BlockingDeque<E> blockingDeque;

    @Inject
    Queue(@QueueModule.QueueSize int size) {
        blockingDeque = new LinkedBlockingDeque<>(size);
    }

    public void addMessage(E message) {
        try {
            blockingDeque.addLast(message);
        } catch (IllegalStateException ex) {
            System.out.println("Queue is full");
        }
    }
}
