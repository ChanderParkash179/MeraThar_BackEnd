package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    List<Hotel> findAllByCityName(String cityName);
    List<Hotel> findAllByOrderByIdAsc();
    Hotel findByName(String name);


}
