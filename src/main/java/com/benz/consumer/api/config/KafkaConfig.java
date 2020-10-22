package com.benz.consumer.api.config;

import com.benz.consumer.api.model.Employee;
import com.benz.consumer.api.model.EmployeeList;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
public class KafkaConfig {

    private ConsumerFactory<String,EmployeeList> consumerFactory()
    {
         JsonDeserializer jsonDeserializer=new JsonDeserializer(EmployeeList.class);
         jsonDeserializer.setRemoveTypeHeaders(false);
         jsonDeserializer.addTrustedPackages("*");
         jsonDeserializer.setUseTypeMapperForKey(true);

        Map<String,Object> config=new ConcurrentHashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_employee");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),jsonDeserializer);
    }

    @Bean
   public ConcurrentKafkaListenerContainerFactory<String,EmployeeList> employeeKafkaListenerContainerFactory()
   {
       ConcurrentKafkaListenerContainerFactory<String,EmployeeList> factory
               =new ConcurrentKafkaListenerContainerFactory<>();
       factory.setConsumerFactory(consumerFactory());
             return factory;
   }
}
