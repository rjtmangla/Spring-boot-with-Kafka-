package com.gupta.config;

import com.gupta.controller.KafkaController;
import com.gupta.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Value("#{'${kafka.producer.topics}'.split(',')}")
    private List<String> kafkaProducerTopics;

    @Value("${kafka.bootstrap.address}")
    private String kafkaBootStrap;

    @Bean
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer(kafkaBootStrap, kafkaProducerTopics);
    }

}
