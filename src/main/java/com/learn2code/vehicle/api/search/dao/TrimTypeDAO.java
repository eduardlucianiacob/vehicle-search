package com.learn2code.vehicle.api.search.dao;

import com.learn2code.vehicle.api.search.entity.TrimType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrimTypeDAO extends JpaRepository<TrimType, Integer> {
}
