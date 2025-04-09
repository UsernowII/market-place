package com.market.statsservice.event;

import com.market.couponservice.model.dto.Item;
import com.market.couponservice.model.dto.ItemsEvent;
import com.market.statsservice.service.EventConsumer;
import com.market.statsservice.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class KafkaItemsListener {

    private final StatsService service;

    @KafkaListener(
            topics = "coupon",
            groupId = "stats-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenCouponRedeemed(ItemsEvent event) {
        log.info("Received ItemsEvent: {}", event);
        List<Item> items = event.getItems();

        service.registerRedeemedItems(items);
    }
}
