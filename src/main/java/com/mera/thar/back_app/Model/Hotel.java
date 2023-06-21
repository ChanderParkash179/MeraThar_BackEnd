package com.mera.thar.back_app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "HOTEL")
@Setter
@Getter
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer id;
    @Column(name = "hotel_name")
    private String name;
    @Column(name = "hotel_address")
    private String address;
    @Column(name = "hotel_phone")
    private String phone;
    @Column(name = "hotel_ratings")
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    public Hotel() {
    }

    public Hotel(String name, String address, String phone, double rating, City city) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.city = city;
    }

    public Hotel(Integer id, String name, String address, String phone, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public Hotel(String name, String address, String phone, double rating) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public Hotel(Integer id, String name, String address, String phone, double rating, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.city = city;
    }
}
