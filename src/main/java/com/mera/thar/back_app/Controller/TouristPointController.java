package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.TouristPointService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/mera_thar/city/tourist-point/")
@RestController
@RequiredArgsConstructor
public class TouristPointController {
    private static final Logger logger = LoggerFactory.getLogger(TouristPointController.class);

    private final TouristPointService touristPointService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.findById() : {}");
            return new ResponseEntity<>(this.touristPointService.getById(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.findByName() : {}");
            return new ResponseEntity<>(this.touristPointService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findTouristPoints() {
        try {
            logger.info("in TouristPointController.findTouristPoints() : {}");
            return new ResponseEntity<>(this.touristPointService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    private ResponseEntity<?> addTouristPoints(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.addTouristPoints() : {}");
            return new ResponseEntity<>(this.touristPointService.save(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    private ResponseEntity<?> updateTouristPoints(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.updateTouristPoints() : {}");
            return new ResponseEntity<>(this.touristPointService.update(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteTouristPoints(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.deleteTouristPoints() : {}");
            return new ResponseEntity<>(this.touristPointService.delete(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/city-name")
    private ResponseEntity<?> getAllTouristPointsByCityName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in TouristPointController.getAllTouristPointsByCityName() : {}");
            return new ResponseEntity<>(this.touristPointService.getAllTouristPointsByCityName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
