package com.market.couponservice.external.client;

import com.market.couponservice.external.client.interfaces.ExternalItemClient;
import com.market.couponservice.external.client.interfaces.TokenManagerService;
import com.market.couponservice.external.response.ItemResponse;

import com.market.couponservice.external.response.ItemWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemClient implements ExternalItemClient {
    private final RestTemplate restTemplate;
    private final TokenManagerService tokenManagerService;

    @Override
    public List<ItemResponse> getItems(List<String> itemIds) {

        String accessToken = tokenManagerService.getAccessToken();

        String ids = String.join(",", itemIds);
        String url = "https://api.mercadolibre.com/items?ids=" + ids;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ItemWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ItemWrapper[].class
        );


        //        return Arrays.stream(response.getBody())
//                .filter(i -> i.getCode() == 200)
//                .map(ItemWrapper::getBody)
//                .filter(item -> item.getPrice() != null)
//                .collect(Collectors.toList());


        List<ItemResponse> items = Arrays.stream(response.getBody())
                .filter(i -> i.getCode() == 200)
                .map(ItemWrapper::getBody)
                .filter(item -> item.getPrice() != null)
                .collect(Collectors.toList());

        if (items.isEmpty()) {
            items = itemIds
                    .stream()
                    .map(id -> ItemResponse.builder()
                            .id(id)
                            .title("Mocked Item")
                            .price(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(10, 101)))
                            .siteId("MCO")
                            .currencyId("COL")
                            .build())
                    .collect(Collectors.toList());

        }

        return items;
    }

}
