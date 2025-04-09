package com.market.statsservice.service;

import com.market.couponservice.model.dto.ItemsEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RedeemedConsumer implements EventConsumer<ItemsEvent> {

    private final StatsService statsService;
    @Override
    public void consume(ItemsEvent event) {
        statsService.registerRedeemedItems(event.getItems());
    };
}
