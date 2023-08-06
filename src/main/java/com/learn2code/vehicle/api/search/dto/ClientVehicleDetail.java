package com.learn2code.vehicle.api.search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientVehicleDetail {
    private int id;

    private String modelYear;

    private String brandName;
    private String modelName;
    private String trimType;
    private String bodyType;
    private double price;
    private String estimatedMonthly;
    private int miles;
    private String dealType;
    private String amountBelowMarket;
    private String location;

    private String seller;
    private String sellerPhone;

}

