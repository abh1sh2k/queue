package com.abhishek.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Queue<E> {
    BlockingDeque<E> blockingDeque;

    Queue(int size) {
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
