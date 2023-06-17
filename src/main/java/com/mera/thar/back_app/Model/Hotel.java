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
    @SequenceGenerator(
            name = "hotel_sequence",
            sequenceName = "hotel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
    @Column(name = "hotel_id")
    private Integer id;
    @Column(name = "hotel_name")
    private String name;
    @Column(name = "hotel_address")
    private String address;
    @Column(name = "hotel_rooms")
    private int numberOfRooms;
    @Column(name = "hotel_ratings")
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    public Hotel() {
    }

    public Hotel(String name, String address, int numberOfRooms, double rating, City city) {
        this.name = name;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rating = rating;
        this.city = city;
    }

    public Hotel(Integer id, String name, String address, int numberOfRooms, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rating = rating;
    }

    public Hotel(String name, String address, int numberOfRooms, double rating) {
        this.name = name;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rating = rating;
    }

    public Hotel(Integer id, String name, String address, int numberOfRooms, double rating, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.rating = rating;
        this.city = city;
    }
}
