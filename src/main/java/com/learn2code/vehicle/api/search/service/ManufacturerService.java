package com.learn2code.vehicle.api.search.service;


import com.learn2code.vehicle.api.search.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    Manufacturer saveManufacturer(Manufacturer manufacturer);

    List<Manufacturer> fetchAllManufacturers();
    Manufacturer getManufacturerForId(int id);
    Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer);
}
