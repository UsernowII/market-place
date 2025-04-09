package com.market.statsservice.mapper;

import com.market.statsservice.model.dto.ItemRequest;
import com.market.statsservice.model.entity.ItemStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "redeemedCount", constant = "1")
    ItemStat toEntity(ItemRequest request);
}
