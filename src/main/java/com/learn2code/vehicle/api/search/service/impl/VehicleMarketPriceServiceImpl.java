package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.VehicleMarketPriceDAO;
import com.learn2code.vehicle.api.search.entity.VehicleMarketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMarketPriceServiceImpl {
    @Autowired
    private VehicleMarketPriceDAO vehicleMarketPriceDAO;

    public VehicleMarketPrice saveVehicleMarketPrice(VehicleMarketPrice vehicleMarketPrice)
    {
        return vehicleMarketPriceDAO.save(vehicleMarketPrice);
    }

    public VehicleMarketPrice getVehicleMarketPriceByBrandModel(String brandName, String modelName){
        return vehicleMarketPriceDAO.findByBrandNameAndModelName(brandName, modelName);

    }
}
