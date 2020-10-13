package com.benz.kafka.consumer.api.listener;

import com.benz.kafka.consumer.api.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"Kafka_Example"},groupId = "group_string")
    public void consume(String message)
    {
        System.out.println("Consumed Message: "+message);
    }

    @KafkaListener(topics = {"Kafka_Example_Json"},groupId = "group_json",containerFactory = "userKafkaListenerContainerFactory")
    public void userConsume(User user)
    {
        System.out.println("User Details -: "+user);
    }
}
