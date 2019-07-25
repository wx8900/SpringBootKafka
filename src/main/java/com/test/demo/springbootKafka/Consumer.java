package com.test.demo.springbootKafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Jack
 * @date 2019/07/24 22:38
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "test", groupId = "group_id")
    public void consume(String message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
    }
}
