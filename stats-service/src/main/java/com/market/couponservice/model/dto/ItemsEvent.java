package com.market.couponservice.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ItemsEvent {
    private List<Item> items;

}
