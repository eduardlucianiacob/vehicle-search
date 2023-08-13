package com.learn2code.vehicle.api.search.config;

import com.learn2code.vehicle.api.search.dto.LoginCredentials;
import com.learn2code.vehicle.api.search.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping
    public String createJwtToken(@RequestBody LoginCredentials loginCredentials)
    {
        if(loginCredentials.getUsername() != null && loginCredentials.getUsername() != ""
                && loginCredentials.getPassword()!=null && loginCredentials.getPassword()!=""){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginCredentials.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return "Please provide valid credentials";
    }

}
