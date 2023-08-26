package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.VehicleService;
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

@RequestMapping("api/mera_thar/city/vehicle/")
@RestController
@RequiredArgsConstructor
public class VehicleController {
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.findById() : {}");
            return new ResponseEntity<>(this.vehicleService.getById(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.findByName() : {}");
            return new ResponseEntity<>(this.vehicleService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findHotels() {
        try {
            logger.info("in VehicleController.findHotels() : {}");
            return new ResponseEntity<>(this.vehicleService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    private ResponseEntity<?> addHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.addHotel() : {}");
            return new ResponseEntity<>(this.vehicleService.save(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    private ResponseEntity<?> updateHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.updateHotel() : {}");
            return new ResponseEntity<>(this.vehicleService.update(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.deleteHotel() : {}");
            return new ResponseEntity<>(this.vehicleService.delete(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/city-name")
    private ResponseEntity<?> getAllHotelsByCityName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in VehicleController.getAllHotelsByCityName() : {}");
            return new ResponseEntity<>(this.vehicleService.getAllVehiclesByCityName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
