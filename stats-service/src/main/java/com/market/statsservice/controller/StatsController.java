package com.market.statsservice.controller;

import com.market.couponservice.model.dto.Item;
import com.market.statsservice.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/stats")
@Log4j2
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public String healthCheck() {
        return "OK STATS";
    }

    @GetMapping("/top-redeemed")
    public ResponseEntity<Map<String, Integer>> getTopRedeemedItems() {
        return ResponseEntity.ok(statsService.getTopRedeemedItems());
    }

    @PostMapping("/redeemed")
    public ResponseEntity<Void> redeemItems(@RequestBody List<Item> items) {
        statsService.registerRedeemedItems(items);
        return ResponseEntity.ok().build();
    }
}
