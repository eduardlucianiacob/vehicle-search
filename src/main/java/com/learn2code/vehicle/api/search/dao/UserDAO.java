package com.learn2code.vehicle.api.search.dao;

import com.learn2code.vehicle.api.search.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<Account, Integer> {


    Account findByUsername(String username);
}
