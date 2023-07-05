package com.mera.thar.back_app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
@Setter
@Getter
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer id;
    @Column(name = "vehicle_name")
    private String name;
    @Column(name = "vehicle_price")
    private String price;
    @Column(name = "vehicle_phone")
    private String phone;
    @Column(name = "vehicle_ratings")
    private double rating;
    @Column(name = "vehicle_type")
    private String type;
    @Column(name = "vehicle_transport")
    private String transport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    public Vehicle() {
    }

    public Vehicle(String name, String price, String phone, String transport, double rating, String type, City city) {
        this.name = name;
        this.price = price;
        this.phone = phone;
        this.transport = transport;
        this.rating = rating;
        this.type = type;
        this.city = city;
    }

    public Vehicle(String name, String price, String phone, String transport, double rating, String type) {
        this.name = name;
        this.price = price;
        this.transport = transport;
        this.phone = phone;
        this.rating = rating;
        this.type = type;
    }

    public Vehicle(Integer id, String name, String price, String transport, String phone, double rating, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.transport = transport;
        this.phone = phone;
        this.rating = rating;
        this.type = type;
    }

    public Vehicle(Integer id, String name, String transport, String price, String phone, double rating, String type, City city) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.phone = phone;
        this.transport = transport;
        this.rating = rating;
        this.type = type;
        this.city = city;
    }
}
