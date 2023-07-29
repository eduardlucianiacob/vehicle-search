package com.learn2code.vehicle.api.search.service;

import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;

import java.util.List;

public interface ModelTrimService {
    Model saveModel(Model model);
    TrimType saveTrimType(TrimType trimType);

    List<Model> getAllModels();

}
