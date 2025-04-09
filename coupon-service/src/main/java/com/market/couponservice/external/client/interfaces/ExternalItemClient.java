package com.market.couponservice.external.client.interfaces;

import com.market.couponservice.external.response.ItemResponse;

import java.util.List;

public interface ExternalItemClient {

    List<ItemResponse> getItems (List<String> itemIds);
}
