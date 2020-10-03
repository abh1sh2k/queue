package com.abhishek.queue;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        QueueModule queueModule = new QueueModule();
        queueModule.setQueueSize(50);
        Injector injector = Guice.createInjector(queueModule);

        Producer producer = injector.getInstance(Producer.class);
        producer.start();

        ConsumerService consumerService = injector.getInstance(ConsumerService.class);
        consumerService.start();

        Thread.sleep(1000);

        consumerService.subscribe(new Consumer("cosumer1"), 1);
        consumerService.subscribe(new Consumer("cosumer2"), 2);

        Thread.sleep(1000);

        consumerService.subscribe(new Consumer("cosumer3"), 3);
        consumerService.subscribe(new Consumer("cosumer1"), 2);

        Thread.sleep(1000);
        consumerService.subscribe(new Consumer("cosumer2"), 3);

    }
}
