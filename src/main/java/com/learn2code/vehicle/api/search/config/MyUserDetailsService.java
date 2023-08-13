package com.learn2code.vehicle.api.search.config;

import com.learn2code.vehicle.api.search.dao.UserDAO;
import com.learn2code.vehicle.api.search.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userDAO.findByUsername(username);

        UserDetails myCustomUser = new MyUserDetails(user);
        return myCustomUser;
    }
}
