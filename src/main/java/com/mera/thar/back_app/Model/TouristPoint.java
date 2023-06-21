package com.mera.thar.back_app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "tourist_point")
@Setter
@Getter
@ToString
public class TouristPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_id")
    private Integer id;
    @Column(name = "tp_name")
    private String name;
    @Column(name = "tp_location")
    private String location;
    @Column(name = "tp_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    public TouristPoint(Integer id, String name, String location, String description, City city) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.city = city;
    }

    public TouristPoint(String name, String location, String description, City city) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.city = city;
    }

    public TouristPoint(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public TouristPoint(Integer id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public TouristPoint() {

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
