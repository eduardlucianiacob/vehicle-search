package com.learn2code.vehicle.api.search.service;

import com.learn2code.vehicle.api.search.dto.ClientVehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetailsDTO;

import java.util.List;

public interface VehicleDetailService {
    List<ClientVehicleDetail> getAllVehicleDetails();
}
