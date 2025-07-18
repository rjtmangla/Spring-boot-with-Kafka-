package com.gupta.controller;

import com.gupta.beans.KafkaRequest;
import com.gupta.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/kafka/")
public class KafkaController {

    @Autowired(required = true)
    private KafkaProducer kafkaProducer;

    @PostMapping("/kafka")
    public String createMessagesOnConsumer(@RequestBody KafkaRequest request) {
        for (int i = 1; i <= request.getNumberOfMessages(); i++) {
            kafkaProducer.publicMessage(request.getTopicName(), createMessage());
        }
        return "Message send";
    }

    private String createMessage() {
        return "Message is at+" + new Date().toString();
    }
}
