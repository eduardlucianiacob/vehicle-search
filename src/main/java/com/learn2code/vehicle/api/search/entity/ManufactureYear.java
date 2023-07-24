package com.learn2code.vehicle.api.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="manufacture_year")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufactureYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int year;
}
