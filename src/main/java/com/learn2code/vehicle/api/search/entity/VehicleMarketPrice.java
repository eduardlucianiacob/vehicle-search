package com.learn2code.vehicle.api.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="market_price")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMarketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String modelYear;
    private String brandName;

    private String modelName;
    private double price;


}
