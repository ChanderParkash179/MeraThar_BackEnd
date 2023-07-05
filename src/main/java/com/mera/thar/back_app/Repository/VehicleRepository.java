package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAllByCityNameOrderByTypeAsc(String cityName);
    List<Vehicle> findAllByOrderByIdAsc();
    Vehicle findByName(String name);
    List<Vehicle> findAllByName(String name);


}
