package com.learn2code.vehicle.api.search.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="manufacturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "* Manufacturer name is required")
    @Column(name="maufacturer_name")
    private String manufacturerName;

    @Column(name="country_of_origin")
    private String countryOfOrigin;

}
