package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Exception.NotFoundException;
import com.mera.thar.back_app.Model.City;
import com.mera.thar.back_app.Model.Hotel;
import com.mera.thar.back_app.Repository.CityRepository;
import com.mera.thar.back_app.Repository.HotelRepository;
import com.mera.thar.back_app.Service.HotelService;
import com.mera.thar.back_app.Util.AppConstants;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Override
    public Response getAllHotelsByCityName(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.getAllHotelsByCityName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("cityName") != null ? (String) input.get("cityName") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("hotels", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_NAME);
                response.setResponseData(responseData);
                return response;
            }
            City getCity = this.cityRepository.findByName(name) != null ? this.cityRepository.findByName(name) : null;

            if (getCity == null) {
                responseData.put("hotels", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            } else {
                List<Hotel> hotels = this.hotelRepository.findAllByCityName(name);
                if (hotels == null) {
                    responseData.put("hotels", null);
                    response.setResponseCode(AppConstants.NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_NAME);
                    response.setResponseData(responseData);
                    return response;
                }
                responseData.put("hotels", hotels);
                response.setResponseCode(AppConstants.OK);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
                response.setResponseData(responseData);
            }
        } catch (NotFoundException ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.getAllHotelsByCityName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.getAllHotelsByCityName() : {} - end");

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        Hotel hotel = null;
        try {
            if (id == null || id == 0) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            }
            hotel = this.hotelRepository.findById(id).get();

            if (hotel == null) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("hotel", hotel);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
            return response;
        } catch (NotFoundException ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.getById() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByName(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.getByName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_NAME);
                response.setResponseData(responseData);
                return response;
            }
            Hotel hotel = this.hotelRepository.findByName(name);
            if (hotel == null) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_NAME);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("hotel", hotel);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.getByName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.getByName() : {} - end");

        return response;
    }

    @Override
    public Response getAll() {

        logger.info("in HotelServiceImpl.getAll() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<Hotel> hotels = this.hotelRepository.findAllByOrderByIdAsc();

            if (hotels == null) {
                responseData.put("hotels", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("hotels", hotels);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.getAll() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.getAll() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String address = (String) input.get("address") != null ? (String) input.get("address") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;
        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        Hotel hotel = null;

        try {
            {
                if (name == null || name.isEmpty()) {
                    responseData.put("hotel", null);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    Hotel alreadyHotelAvailable = this.hotelRepository.findByName(name);

                    if (alreadyHotelAvailable != null) {
                        responseData.put("hotel", alreadyHotelAvailable);
                        response.setResponseCode(AppConstants.BAD_REQUEST);
                        response.setResponseMessage(AppConstants.MSG_RESOURCE_ALREADY_AVAILABLE);
                        response.setResponseData(responseData);
                        return response;
                    } else {

                        City getCity = this.cityRepository.findById(city).get() != null
                                ? this.cityRepository.findById(city).get()
                                : null;

                        if (getCity == null) {
                            responseData.put("hotels", null);
                            response.setResponseCode(AppConstants.NOT_FOUND);
                            response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                            response.setResponseData(responseData);
                            return response;
                        } else {
                            hotel = new Hotel();

                            hotel.setName(name);
                            hotel.setAddress(address);
                            hotel.setRating(rating);
                            hotel.setPhone(phone);
                            hotel.setCity(getCity);

                            this.hotelRepository.save(hotel);

                            responseData.put("hotel", hotel);
                            response.setResponseCode(AppConstants.CREATED);
                            response.setResponseMessage(AppConstants.MSG_DATA_SAVED);
                            response.setResponseData(responseData);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.save() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String address = (String) input.get("address") != null ? (String) input.get("address") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;

        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        Hotel hotel = null;
        try {

            if (id == null || id == 0) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            }
            if (name == null || name.isEmpty()) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_NAME);
                response.setResponseData(responseData);
                return response;
            }

            City getCity = this.cityRepository.findById(city).get();

            hotel = this.hotelRepository.findById(id).get();

            if (hotel == null) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            } else {
                hotel.setName(name);
                hotel.setAddress(address);
                hotel.setRating(rating);
                hotel.setPhone(phone);
                hotel.setCity(getCity);

                this.hotelRepository.save(hotel);
                responseData.put("hotel", hotel);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_UPDATED);
                response.setResponseData(responseData);
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.update() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in HotelServiceImpl.delete() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Hotel hotel = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {

            if (id == null || id == 0) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            }

            hotel = this.hotelRepository.findById(id).get();

            if (hotel.getId() == null) {
                responseData.put("hotel", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_HOTEL_ID);
                response.setResponseData(responseData);
                return response;
            }

            this.hotelRepository.deleteById(id);
            responseData.put("hotel", hotel);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_DELETED);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in HotelServiceImpl.delete() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in HotelServiceImpl.delete() : {} - end");

        return response;
    }
}