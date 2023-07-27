package com.learn2code.vehicle.api.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="trim_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrimType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="trim_type")
    private String trimType;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name="model_trim",
//    joinColumns = @JoinColumn(name="trim_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name="model_id", referencedColumnName = "id")
//    )
//    private List<Model> modelList;


}
