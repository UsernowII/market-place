package com.market.couponservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemsEvent {
    private List<Item> items;

}
