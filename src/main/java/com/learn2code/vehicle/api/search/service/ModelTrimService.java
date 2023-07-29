package com.learn2code.vehicle.api.search.service;

import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.exception.ModelNotFoundException;
import com.learn2code.vehicle.api.search.exception.TrimTypeNotFoundException;

import java.util.List;

public interface ModelTrimService {
    Model saveModel(Model model);
    TrimType saveTrimType(TrimType trimType);

    List<Model> getAllModels();
    Model modifyModel(int id, Model model) throws ModelNotFoundException;
    TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException;
    Model getModelById(int id) throws ModelNotFoundException;
    TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException;

    void deleteModelById(int id) throws ModelNotFoundException;
    void deleteTrimType(int id) throws TrimTypeNotFoundException;

}
