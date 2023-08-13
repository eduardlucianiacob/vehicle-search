package com.learn2code.vehicle.api.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentials {
    private String username;
    private String password;
}
