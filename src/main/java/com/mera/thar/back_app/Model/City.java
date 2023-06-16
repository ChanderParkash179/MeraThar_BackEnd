package com.mera.thar.back_app.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "city")
@Setter
@Getter
@ToString
public class City {

    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_sequence")
    @Column(name = "city_id")
    private Integer id;
    @Column(name = "city_name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TouristPoint> touristPoints;

    public City() {
    }

    public City(Integer id) {
        this.id = id;
    }

    public City(String name) {
        this.name = name;
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}