package com.market.statsservice.service;

public interface EventConsumer<T>{
    void consume(T event);
}
