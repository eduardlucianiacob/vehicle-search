package com.learn2code.vehicle.api.search.dao;

import com.learn2code.vehicle.api.search.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelDAO extends JpaRepository<Model, Integer> {
}
