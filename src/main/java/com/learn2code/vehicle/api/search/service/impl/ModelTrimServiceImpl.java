package com.learn2code.vehicle.api.search.service.impl;

import com.learn2code.vehicle.api.search.dao.ManufacturerDAO;
import com.learn2code.vehicle.api.search.dao.ModelDAO;
import com.learn2code.vehicle.api.search.dao.TrimTypeDAO;
import com.learn2code.vehicle.api.search.entity.Manufacturer;
import com.learn2code.vehicle.api.search.entity.Model;
import com.learn2code.vehicle.api.search.entity.TrimType;
import com.learn2code.vehicle.api.search.exception.ManufacturerNotFoundException;
import com.learn2code.vehicle.api.search.exception.ModelNotFoundException;
import com.learn2code.vehicle.api.search.exception.TrimTypeNotFoundException;
import com.learn2code.vehicle.api.search.service.ModelTrimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ModelTrimServiceImpl implements ModelTrimService {

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private TrimTypeDAO trimTypeDAO;

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Override
    public Model saveModel(Model model) {
        Model savedModel = modelDAO.save(model);
        return savedModel;
    }

    @Override
    public TrimType saveTrimType(TrimType trimType) {
        return trimTypeDAO.save(trimType);
    }

    @Override
    public List<Model> getAllModels() {
        List<Model> savedModels = modelDAO.findAll();
        return savedModels;
    }

    @Override
    public Model modifyModel(int id, Model model) throws ModelNotFoundException {
        Model dbModel = getModelById(id);

        if(dbModel != null && Objects.nonNull(model)) {
            if (Objects.nonNull(model.getModelName()) && !"".equalsIgnoreCase(model.getModelName())) {
                dbModel.setModelName(model.getModelName());
            }
            dbModel = modelDAO.save(dbModel);
        }
        return dbModel;
    }

    @Override
    public TrimType modifyTrimType(int id, TrimType trimType) throws TrimTypeNotFoundException {
        TrimType dbTrimType = getTrimTypeById(id);

        if(dbTrimType != null && Objects.nonNull(trimType)) {
            if (Objects.nonNull(trimType.getTrimType()) && !"".equalsIgnoreCase(trimType.getTrimType())) {
                dbTrimType.setTrimType(trimType.getTrimType());
            }
            dbTrimType = trimTypeDAO.save(dbTrimType);
        }

        return dbTrimType;
    }

    @Override
    public Model getModelById(int id) throws ModelNotFoundException {
        Optional<Model> dbModelOptional = modelDAO.findById(id);
        if(!dbModelOptional.isPresent()){
            throw new ModelNotFoundException("No Model found in DB with ID-"+id);
        }
        return dbModelOptional.get();
    }

    @Override
    public TrimType getTrimTypeById(int id) throws TrimTypeNotFoundException {
        Optional<TrimType> trimTypeOptional = trimTypeDAO.findById(id);

        if(!trimTypeOptional.isPresent()){
            throw new TrimTypeNotFoundException("No Trim Type found in DB with ID-"+id);
        }
        return trimTypeOptional.get();

    }

    @Override
    public void deleteModelById(int id) throws ModelNotFoundException {
        Model dbModel = getModelById(id);
        try {
            modelDAO.deleteById(id);
        } catch(Exception ex){
            System.out.println("*******Unable to delete model. Check DB Connection*******"+ ex.getMessage());
        }

    }

    @Override
    public void deleteTrimType(int id) throws TrimTypeNotFoundException {
        TrimType dbTrim = getTrimTypeById(id);
        try{
            trimTypeDAO.deleteById(id);
        } catch (Exception ex){
            System.out.println("*******Unable to delete trim type. Check DB Connection*******"+ ex.getMessage());
        }
    }

    @Override
    public List<Model> getModelsByManufacturerId(int manufacturerId) throws ManufacturerNotFoundException {
        Optional<Manufacturer> dbManufacturer = manufacturerDAO.findById(manufacturerId);

        if(!dbManufacturer.isPresent()){
            throw new ManufacturerNotFoundException("No manufacturer found for ID-"+manufacturerId);
        }

        List<Model> dbModels = modelDAO.findByManufacturer(dbManufacturer.get());
        return dbModels;
    }

    @Override
    public List<Model> getModelsByManufacturerName(String name) throws ManufacturerNotFoundException {
        Manufacturer dbManufacturer = manufacturerDAO.findByManufacturerName(name);

        if(dbManufacturer == null){
            throw new ManufacturerNotFoundException("No manufacturer found for name-"+name);
        }

        int manufacturerId = dbManufacturer.getId();
        List<Model> dbModels = modelDAO.fetchModelsBasedManufacturerId(manufacturerId);
        return null;
    }


}
