package com.market.couponservice.service;

public interface EventPublisher<T> {

    void publish(T event);
}
