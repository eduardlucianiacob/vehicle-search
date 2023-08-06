package com.learn2code.vehicle.api.search.dao;

import com.learn2code.vehicle.api.search.entity.VehicleMarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMarketPriceDAO extends JpaRepository<VehicleMarketPrice, Integer> {
    VehicleMarketPrice findByBrandNameAndModelName(String brandName, String modelName);
}
