package com.example.queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Broker {

    Map<String, Subscriber> subscriberMap;

    Map<String, Set<String>> consumerDependecies;

    Map<String, Set<String>> consumerTopicMap;

    Set<String> topics;

    int queueSize;

    Broker(int queueSize) {
        this.queueSize = queueSize;
        subscriberMap = new HashMap();
        consumerDependecies = new HashMap();
        consumerTopicMap = new HashMap<>();
        topics = new HashSet();
    }

    public void addConsumers(Subscriber subscriber) {
        subscriberMap.put(subscriber.id, subscriber);
    }

    public void addDependencies(JSONObject dependencies) {
        for (Object id : dependencies.keySet()) {
            String key = (String) id; // key -> consumerid , values -> set<consumerids>
            JSONArray values = (JSONArray) dependencies.get(key);

            Set<String> d = consumerDependecies.getOrDefault(key, new HashSet<String>());

            for (int i = 0; i < values.size(); i++)
                d.add((String) values.get(i));

            consumerDependecies.put(key, d);
        }
    }

    public void subscribe(String topic, String consumerId) {
        topics.add(topic);
        Set<String> set = consumerTopicMap.getOrDefault(topic, new HashSet<>());
        set.add(consumerId);
        consumerTopicMap.put(topic, set);
    }

    public void publish(String topicToPublish, String message) {
        Set<String> consumers = new HashSet<>();
        for (String topic : topics) {
            if (RegexMatcher.match(topic, topicToPublish)) {
                consumers.addAll(consumerTopicMap.get(topic));
            }
        }
        sendAll(message, consumers);
    }


    private void sendAll(String message, Set<String> consumers) {
        Set<String> consumerSent = new HashSet<>();
        for (String consumerId : consumers) {
            if (!consumerSent.contains(consumerId)) {
                sendToOneConsumer(message, consumerId, consumerSent);
            }
        }
    }


    void sendToOneConsumer(String message, String consumerId, Set<String> connsumerSent) {


        Set<String> neighbours = consumerDependecies.get(consumerId);

        if (neighbours != null)
            for (String n : neighbours) {
                if (!connsumerSent.contains(n))
                    sendToOneConsumer(message, n, connsumerSent);
            }

        Subscriber subscriber = subscriberMap.get(consumerId); // adding to the set

        subscriber.recieveMessage(message);

        connsumerSent.add(consumerId);
    }
}
