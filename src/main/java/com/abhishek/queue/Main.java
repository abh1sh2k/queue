package com.abhishek.queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue(50);

        Broker broker = new Broker(queue);

        Producer producer = new Producer(broker);

        producer.start();

        ConsumerService consumerService = new ConsumerService(broker);


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
