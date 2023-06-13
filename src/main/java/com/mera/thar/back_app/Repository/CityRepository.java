package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    City findByName(String cityName);

    List<City> findAllByOrderByIdAsc();

}
