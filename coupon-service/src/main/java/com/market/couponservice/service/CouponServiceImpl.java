package com.market.couponservice.service;

import com.market.couponservice.external.client.interfaces.ExternalItemClient;
import com.market.couponservice.external.response.ItemResponse;
import com.market.couponservice.model.dto.CouponRequest;
import com.market.couponservice.model.dto.CouponResponse;
import com.market.couponservice.model.dto.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CouponServiceImpl implements CouponService{

    private final ExternalItemClient client;
    private final EventPublisher<List<Item>> couponProducer;


    @Override
    public CouponResponse apply(CouponRequest couponRequest) {

        List<ItemResponse> items = client.getItems(
                couponRequest.getItem_ids().stream()
                        .distinct()
                        .toList()
        ).stream()
         .sorted(Comparator.comparing(ItemResponse::getPrice))
         .toList();

        log.info("Items from request: " + items.size());

        BigDecimal total = BigDecimal.ZERO;
        List<Item> selectedItems = new ArrayList<>();

        for (ItemResponse item : items) {
            BigDecimal price = item.getPrice();
            if (total.add(price).compareTo(couponRequest.getAmount()) <= 0) {
                total = total.add(price);
                selectedItems.add(
                        Item.builder()
                            .id(item.getId())
                            .title(item.getTitle())
                            .siteId(item.getSiteId())
                            .currencyId(item.getCurrencyId())
                            .price(price)
                            .build()
                );
            }
        }
        log.info("items valid for redemption: " + items.size());
        couponProducer.publish(selectedItems);

        return CouponResponse.builder()
                .item_ids(selectedItems)
                .total(total)
                .build();
    }
}
