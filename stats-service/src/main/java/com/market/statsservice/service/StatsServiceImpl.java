package com.market.statsservice.service;

import com.market.statsservice.mapper.ItemMapper;
import com.market.couponservice.model.dto.Item;
import com.market.statsservice.repository.StatsRepository;
import com.market.statsservice.model.entity.ItemStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsRepository repository;
    private final ItemMapper itemMapper;

    @Override
    public void registerRedeemedItems(List<Item> items) {
        List<String> itemIds = items.stream()
                .map(Item::getId)
                .toList();

        Map<String, ItemStat> existingItems =
                repository.findAllById(itemIds)
                        .stream()
                        .collect(Collectors.toMap(ItemStat::getId, Function.identity()));

        List<ItemStat> toSave = items.stream()
                .map(item -> {
                    ItemStat existing = existingItems.get(item.getId());
                    if (existing != null) {
                        existing.setRedeemedCount(existing.getRedeemedCount() + 1);
                        return existing;
                    } else {
                        ItemStat newItem = itemMapper.toEntity(item);
                        newItem.setRedeemedCount(1);
                        return newItem;
                    }
                })
                .collect(Collectors.toList());

        repository.saveAll(toSave);

    }

    @Override
    public Map<String, Integer> getTopRedeemedItems() {
        return repository.findTop5ByRedeemedCount().stream()
                .collect(Collectors.toMap(
                        ItemStat::getId,
                        ItemStat::getRedeemedCount,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

    }
}
