package com.market.statsservice.service;

import com.market.statsservice.model.dto.ItemRequest;

import java.util.List;
import java.util.Map;

public interface StatsService {

   void registerRedeemedItems(List<ItemRequest> item);

   Map<String, Integer> getTopRedeemedItems();
}
