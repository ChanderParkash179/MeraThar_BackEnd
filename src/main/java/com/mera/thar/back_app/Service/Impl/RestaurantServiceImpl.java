package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Exception.NotFoundException;
import com.mera.thar.back_app.Model.City;
import com.mera.thar.back_app.Model.Restaurant;
import com.mera.thar.back_app.Repository.CityRepository;
import com.mera.thar.back_app.Repository.RestaurantRepository;
import com.mera.thar.back_app.Service.RestaurantService;
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
public class RestaurantServiceImpl implements RestaurantService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final RestaurantRepository restaurantRepository;
    private final CityRepository cityRepository;

    @Override
    public Response getAllRestaurantsByCityName(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.getAllRestaurantsByCityName() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("cityName") != null ? (String) input.get("cityName") : null;
        try {
            if (name == null || name.isEmpty()) {
                responseData.put("restaurants", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            City getCity = this.cityRepository.findByName(name) != null ? this.cityRepository.findByName(name) : null;

            if (getCity == null) {
                responseData.put("restaurants", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            } else {
                List<Restaurant> restaurants = this.restaurantRepository.findAllByCityName(name);
                if (restaurants == null) {
                    responseData.put("restaurants", null);
                    response.setResponseCode(AppConstants.NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_NAME);
                    response.setResponseData(responseData);
                    return response;
                }
                responseData.put("restaurants", restaurants);
                response.setResponseCode(AppConstants.OK);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
                response.setResponseData(responseData);
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.getById() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        Restaurant restaurant = null;
        try {
            if (id == null || id == 0) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            }
            restaurant = this.restaurantRepository.findById(id).get();

            if (restaurant == null) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("restaurant", restaurant);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getByName(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.getByName() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            Restaurant restaurant = this.restaurantRepository.findByName(name);
            if (restaurant == null) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_NAME);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("restaurant", restaurant);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
            return response;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getAll() {

        logger.info("in RestaurantServiceImpl.getAll() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<Restaurant> restaurants = this.restaurantRepository.findAllByOrderByIdAsc();

            if (restaurants == null) {
                responseData.put("restaurants", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("restaurants", restaurants);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
            return response;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.save() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String address = (String) input.get("address") != null ? (String) input.get("address") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;
        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        Restaurant restaurant = null;

        try {
            {
                if (name == null || name.isEmpty()) {
                    responseData.put("restaurant", null);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    Restaurant alreadyRestaurantAvailable = this.restaurantRepository.findByName(name);

                    if (alreadyRestaurantAvailable != null) {
                        responseData.put("restaurant", alreadyRestaurantAvailable);
                        response.setResponseCode(AppConstants.BAD_REQUEST);
                        response.setResponseMessage(AppConstants.MSG_RESOURCE_ALREADY_AVAILABLE);
                        response.setResponseData(responseData);
                        return response;
                    } else {

                        City getCity = this.cityRepository.findById(city).get() != null
                                ? this.cityRepository.findById(city).get()
                                : null;

                        if (getCity == null) {
                            responseData.put("restaurants", null);
                            response.setResponseCode(AppConstants.NOT_FOUND);
                            response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                            response.setResponseData(responseData);
                            return response;
                        } else {
                            restaurant = new Restaurant();

                            restaurant.setName(name);
                            restaurant.setAddress(address);
                            restaurant.setRating(rating);
                            restaurant.setPhone(phone);
                            restaurant.setCity(getCity);

                            this.restaurantRepository.save(restaurant);

                            responseData.put("restaurant", restaurant);
                            response.setResponseCode(AppConstants.CREATED);
                            response.setResponseMessage(AppConstants.MSG_DATA_SAVED);
                            response.setResponseData(responseData);
                            return response;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.update() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String address = (String) input.get("address") != null ? (String) input.get("address") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;

        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        Restaurant restaurant = null;
        try {

            if (id == null || id == 0) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            }
            if (name == null || name.isEmpty()) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_NAME);
                response.setResponseData(responseData);
                return response;
            }

            City getCity = this.cityRepository.findById(city).get();

            restaurant = this.restaurantRepository.findById(id).get();

            if (restaurant == null) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            } else {
                restaurant.setName(name);
                restaurant.setAddress(address);
                restaurant.setRating(rating);
                restaurant.setPhone(phone);
                restaurant.setCity(getCity);

                this.restaurantRepository.save(restaurant);
                responseData.put("restaurant", restaurant);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_UPDATED);
                response.setResponseData(responseData);
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in RestaurantServiceImpl.delete() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Restaurant restaurant = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {

            if (id == null || id == 0) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            }

            restaurant = this.restaurantRepository.findById(id).get();

            if (restaurant.getId() == null) {
                responseData.put("restaurant", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_RESTAURANT_ID);
                response.setResponseData(responseData);
                return response;
            }

            this.restaurantRepository.deleteById(id);
            responseData.put("restaurant", restaurant);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_DELETED);
            response.setResponseData(responseData);
            return response;
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        }

        return response;
    }
}