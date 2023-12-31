package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@RequestMapping("api/mera_thar/city/hotel/")
@RestController
@RequiredArgsConstructor
public class HotelController {
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    private final HotelService hotelService;

    @PostMapping("get/id")
    private ResponseEntity<?> findById(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.findById() : {}");
            return new ResponseEntity<>(this.hotelService.getById(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/name")
    private ResponseEntity<?> findByName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.findByName() : {}");
            return new ResponseEntity<>(this.hotelService.getByName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/list")
    private ResponseEntity<?> findHotels() {
        try {
            logger.info("in HotelController.findHotels() : {}");
            return new ResponseEntity<>(this.hotelService.getAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("save")
    private ResponseEntity<?> addHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.addHotel() : {}");
            return new ResponseEntity<>(this.hotelService.save(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update")
    private ResponseEntity<?> updateHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.updateHotel() : {}");
            return new ResponseEntity<>(this.hotelService.update(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteHotel(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.deleteHotel() : {}");
            return new ResponseEntity<>(this.hotelService.delete(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("get/city-name")
    private ResponseEntity<?> getAllHotelsByCityName(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in HotelController.getAllHotelsByCityName() : {}");
            return new ResponseEntity<>(this.hotelService.getAllHotelsByCityName(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
