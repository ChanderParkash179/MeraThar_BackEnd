package com.mera.thar.back_app.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "DRIVER")
@Setter
@Getter
@ToString
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer id;
    @Column(name = "driver_name")
    private String name;
    @Column(name = "driver_phone")
    private String phone;
    @Column(name = "driver_ratings")
    private double rating;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Driver() {
    }

    public Driver(String name, String phone, double rating) {
        this.name = name;
        this.phone = phone;
        this.rating = rating;
    }

    public Driver(Integer id, String name, String phone, double rating) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rating = rating;
    }

    public Driver(String name, String phone, double rating, Vehicle vehicle) {
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.vehicle = vehicle;
    }

    public Driver(Integer id, String name, String phone, double rating, Vehicle vehicle) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.vehicle = vehicle;
    }
}
