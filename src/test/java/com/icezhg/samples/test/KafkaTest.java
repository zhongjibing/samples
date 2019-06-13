package com.icezhg.samples.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by zhongjibing on 2019/06/14.
 */
public class KafkaTest extends SamplesTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    public void testSend() {
        kafkaTemplate.send("test", "this is a test");
    }


}
