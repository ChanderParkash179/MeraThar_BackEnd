package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Exception.NotFoundException;
import com.mera.thar.back_app.Model.City;
import com.mera.thar.back_app.Model.TouristPoint;
import com.mera.thar.back_app.Repository.CityRepository;
import com.mera.thar.back_app.Repository.TouristPointRepository;
import com.mera.thar.back_app.Service.TouristPointService;
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
public class TouristPointServiceImpl implements TouristPointService {

    private static final Logger logger = LoggerFactory.getLogger(TouristPointServiceImpl.class);

    private final TouristPointRepository touristPointRepository;
    private final CityRepository cityRepository;

    @Override
    public Response getAllTouristPointsByCityName(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.getAllTouristPointsByCityName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("cityName") != null ? (String) input.get("cityName") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("places", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            City getCity = this.cityRepository.findByName(name) != null ? this.cityRepository.findByName(name) : null;

            if (getCity == null) {
                responseData.put("places", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            } else {
                List<TouristPoint> places = this.touristPointRepository.findAllByCityName(name);
                if (places == null) {
                    responseData.put("places", null);
                    response.setResponseCode(AppConstants.NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_NAME);
                    response.setResponseData(responseData);
                    return response;
                }
                responseData.put("places", places);
                response.setResponseCode(AppConstants.OK);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
                response.setResponseData(responseData);
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.getAllTouristPointsByCityName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.getAllTouristPointsByCityName() : {} - end");

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        TouristPoint place = null;
        try {
            if (id == null || id == 0) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            }
            place = this.touristPointRepository.findById(id).get();

            if (place == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("place", place);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.getById() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByName(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.getByName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            TouristPoint place = this.touristPointRepository.findByName(name);
            if (place == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("place", place);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.getByName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.getByName() : {} - end");

        return response;
    }

    @Override
    public Response getAll() {

        logger.info("in TouristPointServiceImpl.getAll() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<TouristPoint> places = this.touristPointRepository.findAllByOrderByIdAsc();

            if (places == null) {
                responseData.put("places", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("places", places);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.getAll() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.getAll() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String location = (String) input.get("location") != null ? (String) input.get("location") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;
        String city = (String) input.get("city") != null ? (String) input.get("city") : null;

        TouristPoint place = null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else {

                TouristPoint alreadyTouristPointAvailable = this.touristPointRepository.findByName(name);

                if (alreadyTouristPointAvailable != null) {
                    responseData.put("place", alreadyTouristPointAvailable);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_RESOURCE_ALREADY_AVAILABLE);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    City getCity = this.cityRepository.findByName(city) != null
                            ? this.cityRepository.findByName(city)
                            : null;

                    if (getCity == null) {
                        responseData.put("place", null);
                        response.setResponseCode(AppConstants.NOT_FOUND);
                        response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                        response.setResponseData(responseData);
                        return response;
                    } else {
                        place = new TouristPoint();

                        place.setName(name);
                        place.setLocation(location);
                        place.setDescription(description);
                        place.setCity(getCity);

                        this.touristPointRepository.save(place);

                        responseData.put("place", place);
                        response.setResponseCode(AppConstants.CREATED);
                        response.setResponseMessage(AppConstants.MSG_DATA_SAVED);
                        response.setResponseData(responseData);

                    }
                }
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.save() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String location = (String) input.get("location") != null ? (String) input.get("location") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;

        String city = input.get("city") != null ? (String) input.get("city") : null;

        TouristPoint place = null;
        try {
            if (id == null || id == 0) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            }
            if (name == null || name.isEmpty()) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_NAME);
                response.setResponseData(responseData);
                return response;
            }

            City getCity = this.cityRepository.findByName(city) != null
                    ? this.cityRepository.findByName(city)
                    : null;

            place = this.touristPointRepository.findById(id).get();

            if (place == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            } else {
                place.setName(name);
                place.setLocation(location);
                place.setDescription(description);
                place.setCity(getCity);

                this.touristPointRepository.save(place);
                responseData.put("place", place);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_UPDATED);
                response.setResponseData(responseData);

            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.update() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in TouristPointServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        TouristPoint place = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {
            if (id == null || id == 0) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            }

            place = this.touristPointRepository.findById(id).get();

            if (place.getId() == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_TOURIST_POINT_ID);
                response.setResponseData(responseData);
                return response;
            }

            this.touristPointRepository.deleteById(id);
            responseData.put("place", place);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_DELETED);
            response.setResponseData(responseData);

        } catch (NotFoundException ex) {
            logger.error("" + ex);
            logger.error("in TouristPointServiceImpl.delete() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in TouristPointServiceImpl.delete() : {} - end");

        return response;
    }
}