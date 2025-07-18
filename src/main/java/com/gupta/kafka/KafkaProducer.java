package com.gupta.kafka;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.*;

public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Set<String> topics;

    public KafkaProducer(String bootstrap, List<String> topics) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties));
        this.topics = new HashSet<>(topics);
    }

    public void publicMessage(String topicName, String message) {
        if (topics.contains(topicName)) {
            kafkaTemplate.send(topicName, message);
        }else {
            throw new TopicExistsException("Producer not available");
        }
    }

}
