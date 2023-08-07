package com.learn2code.vehicle.api.search.service;

import com.learn2code.vehicle.api.search.dto.ClientVehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetailsDTO;
import com.learn2code.vehicle.api.search.exception.VehicleDetailsNotFound;

import java.util.List;

public interface VehicleDetailService {
    List<ClientVehicleDetail> getAllVehicleDetails();
    VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound;
}
