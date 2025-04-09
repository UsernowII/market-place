package com.market.statsservice.repository;

import com.market.statsservice.model.entity.ItemStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatsRepository extends JpaRepository<ItemStat, String> {

    @Query("SELECT i FROM ItemStat i ORDER BY i.redeemedCount DESC LIMIT 5")
    List<ItemStat> findTop5ByRedeemedCount();
}
