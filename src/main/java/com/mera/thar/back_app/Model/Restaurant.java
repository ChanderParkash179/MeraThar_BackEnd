package com.mera.thar.back_app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
@Setter
@Getter
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer id;
    @Column(name = "restaurant_name")
    private String name;
    @Column(name = "restaurant_address")
    private String address;
    @Column(name = "restaurant_phone")
    private String phone;
    @Column(name = "restaurant_ratings")
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String phone, double rating, City city) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.city = city;
    }

    public Restaurant(Integer id, String name, String address, String phone, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public Restaurant(String name, String address, String phone, double rating) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public Restaurant(Integer id, String name, String address, String phone, double rating, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.city = city;
    }
}
