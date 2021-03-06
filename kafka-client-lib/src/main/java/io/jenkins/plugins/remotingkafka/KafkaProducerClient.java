package io.jenkins.plugins.remotingkafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;
import java.util.logging.Logger;

public class KafkaProducerClient {
    private static final Logger LOGGER = Logger.getLogger(KafkaProducerClient.class.getName());
    private static volatile KafkaProducerClient instance;
    private volatile Producer<String, byte[]> byteProducer;

    private KafkaProducerClient() {

    }

    public static KafkaProducerClient getInstance() {
        if (instance == null) {
            synchronized (KafkaProducerClient.class) {
                if (instance == null) instance = new KafkaProducerClient();
            }
        }
        return instance;
    }

    public Producer<String, byte[]> getByteProducer(Properties producerProps) {
        if (byteProducer == null) {
            synchronized (KafkaProducerClient.class) {
                if (byteProducer == null) byteProducer = new KafkaProducer<>(producerProps);
            }
        }
        return byteProducer;
    }
}
