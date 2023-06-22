package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findAllByCityName(String cityName);
    List<Restaurant> findAllByOrderByIdAsc();
    Restaurant findByName(String name);


}
