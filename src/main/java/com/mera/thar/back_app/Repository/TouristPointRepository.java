package com.mera.thar.back_app.Repository;

import com.mera.thar.back_app.Model.TouristPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristPointRepository extends JpaRepository<TouristPoint, Integer> {

}
