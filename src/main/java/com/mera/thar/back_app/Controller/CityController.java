package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.CityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@RequestMapping("api/mera_thar/city/")
@RestController
@RequiredArgsConstructor
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in CityController.findById() : {}");
            return new ResponseEntity<>(this.cityService.getById(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in CityController.findByName() : {}");
            return new ResponseEntity<>(this.cityService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findCities() {
        try {
            logger.info("in CityController.findCities() : {}");
            return new ResponseEntity<>(this.cityService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    private ResponseEntity<?> addCity(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in CityController.addCity() : {}");
            return new ResponseEntity<>(this.cityService.save(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    private ResponseEntity<?> updateCity(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in CityController.updateCity() : {}");
            return new ResponseEntity<>(this.cityService.update(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteCity(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in CityController.deleteCity() : {}");
            return new ResponseEntity<>(this.cityService.delete(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
