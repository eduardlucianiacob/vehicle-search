package com.learn2code.vehicle.api.search.controller;

import com.learn2code.vehicle.api.search.entity.VehicleMarketPrice;
import com.learn2code.vehicle.api.search.service.impl.VehicleMarketPriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle-market-price")
public class VehicleMarketPriceController {

    @Autowired
    private VehicleMarketPriceServiceImpl vehicleMarketPriceService;

    @PostMapping
    public ResponseEntity<VehicleMarketPrice> saveVehicleMarketPrice(@RequestBody VehicleMarketPrice vehicleMarketPrice){
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleMarketPriceService.saveVehicleMarketPrice(vehicleMarketPrice));
    }

}
