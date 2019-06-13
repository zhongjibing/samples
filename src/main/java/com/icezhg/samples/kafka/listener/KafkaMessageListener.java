package com.icezhg.samples.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2019/06/14.
 */
@Slf4j
@Component
public class KafkaMessageListener {

//    @KafkaListener(topics = "test", groupId = "test-consumer-group")
//    public void listenOnTest(ConsumerRecord<?, ?> record) throws Exception {
//        log.info("topic={}, offset={}, value={}", record.topic(), record.offset(), record.value());
//    }

    @KafkaListener(topics = "test", groupId = "test-consumer-group")
    public void listenOnTest(String message) throws Exception {
        log.info("topic={}, offset={}, value={}", message, message, message);
    }
}
