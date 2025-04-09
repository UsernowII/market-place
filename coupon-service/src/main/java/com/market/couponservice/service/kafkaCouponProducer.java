package com.market.couponservice.service;

import com.market.couponservice.model.dto.Item;
import com.market.couponservice.model.dto.ItemsEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class kafkaCouponProducer implements  EventPublisher<List<Item>>{

    private final KafkaTemplate<String, ItemsEvent> kafkaTemplate;
    @Override
    public void publish(List<Item> items) {
        ItemsEvent event =  new ItemsEvent(items);
        log.info("Send Messages Items {}:", event);
        kafkaTemplate.send("coupon", event);
        log.info("Send Messages Items {}:", event);

    }
}
