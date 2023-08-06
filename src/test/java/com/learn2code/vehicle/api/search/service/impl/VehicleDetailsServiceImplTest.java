package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dto.ClientVehicleDetail;
import com.learn2code.vehicle.api.search.service.VehicleDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleDetailsServiceImplTest {

    @Autowired
    private VehicleDetailService vehicleDetailService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllVehicleDetails() {
        List<ClientVehicleDetail> allClientVehicles = vehicleDetailService.getAllVehicleDetails();
        assertEquals(allClientVehicles.get(0).getEstimatedMonthly(), "$424.4758333333333/monthly est.");
        assertEquals(allClientVehicles.get(1).getDealType(),"Great Deal");
        assertEquals(allClientVehicles.get(2).getAmountBelowMarket(), "$9870906.575below market price.");


    }
}