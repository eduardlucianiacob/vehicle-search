package com.learn2code.vehicle.api.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="models")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="model_name")
    private String modelName;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name="model_trim",
            joinColumns = @JoinColumn(name="model_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="trim_id", referencedColumnName = "id")
    )
    private List<TrimType> trimTypeList;

    @ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    private Manufacturer manufacturer;
}
