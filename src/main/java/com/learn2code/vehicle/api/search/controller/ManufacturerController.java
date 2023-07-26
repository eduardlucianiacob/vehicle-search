package com.learn2code.vehicle.api.search.controller;

import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.exception.ManufacturerNotFoundException;
import com.learn2code.vehicle.api.search.exception.MissingFieldException;
import com.learn2code.vehicle.api.search.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerBasedOnId(@PathVariable int id) throws ManufacturerNotFoundException {
        Manufacturer dbManufacturer = manufacturerService.getManufacturerForId(id);
        if(dbManufacturer == null){
            throw new ManufacturerNotFoundException("No manufacturer found for ID-"+id);
        }

        return ResponseEntity.ok(dbManufacturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacture(@PathVariable int id, @Valid @RequestBody Manufacturer manufacturer, BindingResult result) throws ManufacturerNotFoundException, MissingFieldException {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            throw new MissingFieldException(errors.get(0).getDefaultMessage());
        }
        Manufacturer updatedManufacturer = manufacturerService.updateManufacturer(id, manufacturer);

        if(updatedManufacturer == null){
            throw new ManufacturerNotFoundException("No manufacturer found for ID -" + id);
        }

        return new ResponseEntity<>(updatedManufacturer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManufacturerById(@PathVariable int id) throws ManufacturerNotFoundException {
        manufacturerService.deleteManufacturerById(id);
        return new ResponseEntity<>("Manufacturer DELETED from DB with ID-"+id,HttpStatus.OK);
    }
}
