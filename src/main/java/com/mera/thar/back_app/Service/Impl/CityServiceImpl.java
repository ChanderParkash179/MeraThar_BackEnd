package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Exception.NotFoundException;
import com.mera.thar.back_app.Model.City;
import com.mera.thar.back_app.Repository.CityRepository;
import com.mera.thar.back_app.Service.CityService;
import com.mera.thar.back_app.Util.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in CityServiceImpl.getById() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        City city = null;
        try {
            if (id == null || id == 0) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            }
            city = this.cityRepository.findById(id).get();

            if (city.getId() == null) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("city", city);
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

        logger.info("in CityServiceImpl.getByName() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            }
            City city = this.cityRepository.findByName(name);
            if (city == null) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("city", city);
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

        logger.info("in CityServiceImpl.getAll() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<City> cities = this.cityRepository.findAllByOrderByIdAsc();

            if (cities == null) {
                responseData.put("cities", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("cities", cities);
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

        logger.info("in CityServiceImpl.save() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        City city = null;
        try {
            {
                if (name == null || name.isEmpty()) {
                    responseData.put("city", null);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    City alreadyCityAvailable = this.cityRepository.findByName(name);

                    if (alreadyCityAvailable != null) {
                        responseData.put("city", alreadyCityAvailable);
                        response.setResponseCode(AppConstants.BAD_REQUEST);
                        response.setResponseMessage(AppConstants.MSG_RESOURCE_ALREADY_AVAILABLE);
                        response.setResponseData(responseData);
                        return response;
                    }
                    city = new City(name);
                    this.cityRepository.save(city);

                    responseData.put("city", city);
                    response.setResponseCode(AppConstants.CREATED);
                    response.setResponseMessage(AppConstants.MSG_DATA_SAVED);
                    response.setResponseData(responseData);
                    return response;

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in CityServiceImpl.update() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        City city = null;
        try {

            if (id == null || id == 0) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            }
            if (name == null || name.isEmpty()) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            }

            city = this.cityRepository.findById(id).get();

            if (city == null) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            } else {
                city.setName(name);
                this.cityRepository.save(city);
                responseData.put("city", city);
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

        logger.info("in CityServiceImpl.delete() : {}");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        City city = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {

            if (id == null || id == 0) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            }

            city = this.cityRepository.findById(id).get();

            if (city.getId() == null) {
                responseData.put("city", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_ID);
                response.setResponseData(responseData);
                return response;
            }

            this.cityRepository.deleteById(id);
            responseData.put("city", city);
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
