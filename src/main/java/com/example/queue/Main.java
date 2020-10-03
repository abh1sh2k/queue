package com.example.queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader("src/main/resources/config.json"));


            JSONArray consumers = (JSONArray) obj.get("consumers");


            int queueSize =  Integer.parseInt((String)obj.get("queue_size"));

            System.out.println(queueSize);

            Broker broker = new Broker(queueSize);

            for (int i = 0; i < consumers.size(); i++) {

                String consumerId = (String) consumers.get(i);
                Subscriber subscriber = new Subscriber(consumerId);
                broker.addConsumers(subscriber);
            }

            JSONObject dependencies = (JSONObject) obj.get("dependencies");
            broker.addDependencies(dependencies);

            broker.subscribe("phone(.)*", "A");
            broker.subscribe("phone", "A");
            broker.subscribe("phonepe", "B");
            broker.subscribe("phone", "C");


            String messageStr = "phonepe memory queue coding interview";


            Publisher publisher = new Publisher(broker);
            for (String message : messageStr.split("\\s+")) {
                publisher.publish("phonepe", message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
