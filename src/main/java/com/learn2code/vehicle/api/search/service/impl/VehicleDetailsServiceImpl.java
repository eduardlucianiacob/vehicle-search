package com.learn2code.vehicle.api.search.service.impl;

import ch.qos.logback.core.net.server.Client;
import com.learn2code.vehicle.api.search.dto.ClientVehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetail;
import com.learn2code.vehicle.api.search.dto.VehicleDetailsDTO;
import com.learn2code.vehicle.api.search.entity.VehicleMarketPrice;
import com.learn2code.vehicle.api.search.exception.VehicleDetailsNotFound;
import com.learn2code.vehicle.api.search.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VehicleDetailsServiceImpl implements VehicleDetailService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private VehicleMarketPriceServiceImpl vehicleMarketPriceService;

    @Override
    public List<ClientVehicleDetail> getAllVehicleDetails() {
        VehicleDetailsDTO vehicleDetailsDTO = restTemplate.getForObject("http://localhost:9195/api/v1/vehicle-details",VehicleDetailsDTO.class);

//         List<ClientVehicleDetail> clientVehicleDetailsList = new ArrayList<>();
//                for(VehicleDetail vehicleDetail: vehicleDetailsDTO.getVehicleDetailList()){
//
//                clientVehicleDetailsList.add(mapClientVehicleDetailFromVehicleDetail(vehicleDetail));
//                }
//
//        return clientVehicleDetailsList;

        List<ClientVehicleDetail> clientVehicleDetailsList = vehicleDetailsDTO.getVehicleDetailList()
                .stream()
                .map(vehicle-> mapClientVehicleDetailFromVehicleDetail(vehicle))
                .collect(Collectors.toList());

        return clientVehicleDetailsList;
    }

    @Override
    public VehicleDetail getVehicleById(int vehicleId) throws VehicleDetailsNotFound {
        VehicleDetail dbVehicle = null;
        try{
            dbVehicle = restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details/"+vehicleId, VehicleDetail.class);
        } catch (Exception e){
            throw new VehicleDetailsNotFound("No vehicle details found in DB for ID-"+ vehicleId);
        }
        return dbVehicle;
    }

    @Override
    public List<ClientVehicleDetail> fetchVehicleDetailByCriteria(String modelYear, String brand, String model, String trim, String price) {
        Map<String, String> params = new HashMap<>();
        params.put("modelYear", modelYear);
        params.put("brand", brand);
        params.put("model", model);
        params.put("trim", trim);
        params.put("price", price);

        String url = "http://localhost:9194/api/v1/vehicle-details/search?modelYear={modelYear}&brand={brand}&model={model}&trim={trim}&price={price}";
        VehicleDetailsDTO filteredList = restTemplate.getForObject(url, VehicleDetailsDTO.class, params);
        return filteredList.getVehicleDetailList().stream().map(vehicleDetail -> mapClientVehicleDetailFromVehicleDetail(vehicleDetail)).collect(Collectors.toList());
    }

    private ClientVehicleDetail mapClientVehicleDetailFromVehicleDetail(VehicleDetail vehicleDetail){
        ClientVehicleDetail clientVehicleDetail = new ClientVehicleDetail();
        clientVehicleDetail.setId(vehicleDetail.getId());
        clientVehicleDetail.setModelYear(vehicleDetail.getModelYear());
        clientVehicleDetail.setBrandName(vehicleDetail.getBrandName());
        clientVehicleDetail.setModelName(vehicleDetail.getModelName());
        clientVehicleDetail.setTrimType(vehicleDetail.getTrimType());
        clientVehicleDetail.setBodyType(vehicleDetail.getBodyType());
        clientVehicleDetail.setPrice(vehicleDetail.getPrice());
        clientVehicleDetail.setMiles(vehicleDetail.getMiles());
        clientVehicleDetail.setLocation(vehicleDetail.getLocation());
        clientVehicleDetail.setSeller(vehicleDetail.getSeller());
        clientVehicleDetail.setSellerPhone(vehicleDetail.getSellerPhone());

        // calculate estimated monthly price
        double monthlyPrice = vehicleDetail.getPrice()/(5*12) + vehicleDetail.getPrice()*vehicleDetail.getInterestRate()/(100*12);
        clientVehicleDetail.setEstimatedMonthly("$"+monthlyPrice+"/monthly est.");

        // Calculate Amount below or above market place

        // calculate current market price
        VehicleMarketPrice dbVehicleMarketPrice = vehicleMarketPriceService.getVehicleMarketPriceByBrandModel(vehicleDetail.getBrandName(), vehicleDetail.getModelName());

        double priceReductionForAge = (LocalDate.now().getYear()- Integer.parseInt(vehicleDetail.getModelYear())
                * dbVehicleMarketPrice.getPrice()*0.5/25);

        double priceReductionForMiles = (vehicleDetail.getMiles()*dbVehicleMarketPrice.getPrice()* 0.75 /500000);


        double currentVehicleMarketPrice = dbVehicleMarketPrice.getPrice() - priceReductionForMiles - priceReductionForAge;

        if(currentVehicleMarketPrice<0) {
            currentVehicleMarketPrice = 0;
        }

        double marketPriceComparison = currentVehicleMarketPrice - vehicleDetail.getPrice();
        if(marketPriceComparison > 0){
            clientVehicleDetail.setAmountBelowMarket("$"+ marketPriceComparison + "below market price.");
        } else{
            clientVehicleDetail.setAmountBelowMarket("$"+ Math.abs(marketPriceComparison) + " above market price.");
        }

        if(marketPriceComparison>800){
            clientVehicleDetail.setDealType("Great Deal");
        } else if(marketPriceComparison>350 && marketPriceComparison<=800 ){
            clientVehicleDetail.setDealType("Good Deal");
        } else if(marketPriceComparison>-100 && marketPriceComparison<=350)
            clientVehicleDetail.setDealType("Fair Deal");
        else {
            clientVehicleDetail.setDealType("Bad Deal");
        }

        return clientVehicleDetail;
    }

}
