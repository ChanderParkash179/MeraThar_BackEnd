package com.mera.thar.back_app.Model;

import lombok.*;
import javax.persistence.*;

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

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}