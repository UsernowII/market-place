package com.market.statsservice.service;

import com.market.couponservice.model.dto.Item;

import java.util.List;
import java.util.Map;

public interface StatsService {

   void registerRedeemedItems(List<Item> item);

   Map<String, Integer> getTopRedeemedItems();
}
