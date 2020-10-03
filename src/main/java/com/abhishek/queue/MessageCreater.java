package com.abhishek.queue;

import com.example.queue.Message;

import java.util.Random;

//this class is just for demo to used in publisher to create message
public class MessageCreater {

    static Random random = new Random();
    static int i = 0;


    static Message getMessage() {
        int timeToExpire = 10000 + random.nextInt(10000);
        int topic = random.nextInt(3);
        Message message = new Message("body-" + i, "topic-" + topic, timeToExpire);
        return message;
    }
}
