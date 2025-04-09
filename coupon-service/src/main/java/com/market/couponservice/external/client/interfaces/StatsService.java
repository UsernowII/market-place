package com.market.couponservice.external.client.interfaces;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CircuitBreaker( name = "external", fallbackMethod = "fallback")
@FeignClient(name = "STATS-SERVICE", path = "/stats")
public interface StatsService {


    @PostMapping
    ResponseEntity<Long> redeemed(@RequestBody List<String> itemIds);

}
