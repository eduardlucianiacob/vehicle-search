package com.learn2code.vehicle.api.search.controller;

import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class ManufacturerController {


    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturerInDB(@RequestBody Manufacturer manufacturer)
    {
        Manufacturer dbRecord = manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<>(dbRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> savedManufacturers = manufacturerService.fetchAllManufacturers();
        return ResponseEntity.status(HttpStatus.OK).body(savedManufacturers);

    }
}
