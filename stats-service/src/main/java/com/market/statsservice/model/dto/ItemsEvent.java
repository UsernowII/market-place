package com.market.statsservice.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsEvent {
    private List<Item> items;

}