package com.market.couponservice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
@Log4j2
public class CouponController {


    @GetMapping
    public String healthCheck() {
       return "OK";
    }
}
