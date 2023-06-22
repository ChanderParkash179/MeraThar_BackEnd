package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/city/restaurant/")
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.getById(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findHotels() {
        try {
            return new ResponseEntity<>(this.restaurantService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    private ResponseEntity<?> addHotel(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.save(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    private ResponseEntity<?> updateHotel(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.update(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteHotel(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.delete(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/city-name")
    private ResponseEntity<?> getAllHotelsByCityName(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.restaurantService.getAllRestaurantsByCityName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
