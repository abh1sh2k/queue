package com.abhishek.queue;

import com.example.queue.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumerService extends Thread {
    Map<String, List<Consumer>> consumerMap;
    Broker broker;

    ConsumerService(Broker broker) {
        this.broker = broker;
        this.consumerMap = new HashMap<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (Exception ex) {

            }

            Message message = broker.consume();
            if (message == null)
                System.out.println(" no messages in que");
            else if (System.currentTimeMillis() > message.getTimeToExpire()) {
                System.out.println(message.body + " has expired");
            } else {
                List<Consumer> consumers = consumerMap.get(message.getTopic());
                if (consumers == null) {
                    System.out.println(" message for topic " + message.getTopic() + " discarded");
                } else {
                    consumers.forEach(consumer -> {
                        consumer.recieve(message);
                    });
                }
            }
        }
    }

    public void subscribe(Consumer consumer, int topic) {
        String topicId = "topic-" + topic;
        synchronized (this) {
            List<Consumer> consumers = consumerMap.getOrDefault(topicId, new ArrayList<>());
            consumers.add(consumer);
            consumerMap.put(topicId, consumers);
        }
    }
}
