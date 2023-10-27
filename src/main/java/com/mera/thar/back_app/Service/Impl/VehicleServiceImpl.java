package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Exception.NotFoundException;
import com.mera.thar.back_app.Model.City;
import com.mera.thar.back_app.Model.Vehicle;
import com.mera.thar.back_app.Repository.CityRepository;
import com.mera.thar.back_app.Repository.VehicleRepository;
import com.mera.thar.back_app.Service.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;
    private final CityRepository cityRepository;

    @Override
    public Response getAllVehiclesByCityName(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.getAllVehiclesByCityName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("cityName") != null ? (String) input.get("cityName") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("vehicles", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_NAME);
                response.setResponseData(responseData);
                return response;
            }
            City getCity = this.cityRepository.findByName(name) != null ? this.cityRepository.findByName(name) : null;

            if (getCity == null) {
                responseData.put("vehicles", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_CITY_NAME);
                response.setResponseData(responseData);
                return response;
            } else {
                List<Vehicle> vehicles = this.vehicleRepository.findAllByCityNameOrderByTypeAsc(name);
                if (vehicles == null) {
                    responseData.put("vehicles", null);
                    response.setResponseCode(AppConstants.NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_NAME);
                    response.setResponseData(responseData);
                    return response;
                }
                responseData.put("vehicles", vehicles);
                response.setResponseCode(AppConstants.OK);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
                response.setResponseData(responseData);
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.getAllVehiclesByCityName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.getAllVehiclesByCityName() : {} - end");

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.getById() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        Vehicle vehicle = null;
        try {
            if (id == null || id == 0) {
                responseData.put("vehicles", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            }
            vehicle = this.vehicleRepository.findById(id).get();

            if (vehicle == null) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("vehicle", vehicle);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.getById() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.getById() : {} - end");

        return response;
    }

    @Override
    public Response getByName(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.getByName() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (name == null || name.isEmpty()) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_NAME);
                response.setResponseData(responseData);
                return response;
            }
            List<Vehicle> vehicle = this.vehicleRepository.findAllByName(name);
            if (vehicle == null) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_NAME);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("vehicle", vehicle);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.getByName() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.getByName() : {} - end");

        return response;
    }

    @Override
    public Response getAll() {

        logger.info("in VehicleServiceImpl.getAll() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        try {
            List<Vehicle> vehicles = this.vehicleRepository.findAllByOrderByIdAsc();

            if (vehicles == null) {
                responseData.put("vehicles", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                response.setResponseData(responseData);
                return response;
            }
            responseData.put("vehicles", vehicles);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_FOUND);
            response.setResponseData(responseData);

        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.getAll() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.getAll() : {} - end");

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.save() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String price = (String) input.get("price") != null ? (String) input.get("price") : null;
        String type = (String) input.get("type") != null ? (String) input.get("type") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;
        String city = (String) input.get("city") != null ? (String) input.get("city") : null;

        Vehicle vehicle = null;

        try {

            if (name == null || name.isEmpty()) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else {

                Vehicle alreadyVehicleAvailable = this.vehicleRepository.findByName(name);

                if (alreadyVehicleAvailable != null) {
                    responseData.put("vehicle", alreadyVehicleAvailable);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_RESOURCE_ALREADY_AVAILABLE);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    City getCity = this.cityRepository.findByName(city) != null
                            ? this.cityRepository.findByName(city)
                            : null;

                    if (getCity == null) {
                        responseData.put("vehicle", null);
                        response.setResponseCode(AppConstants.NOT_FOUND);
                        response.setResponseMessage(AppConstants.MSG_RESOURCE_NOT_FOUND);
                        response.setResponseData(responseData);
                        return response;
                    } else {
                        vehicle = new Vehicle();

                        vehicle.setName(name);
                        vehicle.setPrice(price);
                        vehicle.setType(type);
                        vehicle.setRating(rating);
                        vehicle.setPhone(phone);
                        vehicle.setCity(getCity);

                        if (vehicle.getType().equals(("BUSS").toLowerCase())
                                || vehicle.getType().equals(("RIKSHAW").toLowerCase())) {
                            vehicle.setTransport("PUBLIC");
                        } else {
                            vehicle.setTransport("PRIVATE");
                        }

                        this.vehicleRepository.save(vehicle);

                        responseData.put("vehicle", vehicle);
                        response.setResponseCode(AppConstants.CREATED);
                        response.setResponseMessage(AppConstants.MSG_DATA_SAVED);
                        response.setResponseData(responseData);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.save() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.save() : {} - end");

        return response;
    }

    @Override
    public Response update(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.update() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String type = (String) input.get("type") != null ? (String) input.get("type") : null;
        String price = (String) input.get("price") != null ? (String) input.get("price") : null;
        Double rating = (Double) input.get("rating") != 0 ? (Double) input.get("rating") : 0;
        String phone = (String) input.get("phone") != null ? (String) input.get("phone") : null;

        String city = input.get("city") != null ? (String) input.get("city") : null;

        Vehicle vehicle = null;
        try {

            if (id == null || id == 0) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            }
            if (name == null || name.isEmpty()) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_NAME);
                response.setResponseData(responseData);
                return response;
            }

            City getCity = this.cityRepository.findByName(city) != null
                    ? this.cityRepository.findByName(city)
                    : null;

            vehicle = this.vehicleRepository.findById(id).get();

            if (vehicle == null) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            } else {
                vehicle.setName(name);
                vehicle.setPrice(price);
                vehicle.setRating(rating);
                vehicle.setPhone(phone);
                vehicle.setCity(getCity);
                vehicle.setType(type);

                if (vehicle.getType().equals(("BUS"))
                        || vehicle.getType().equals(("RIKSHAW"))) {
                    vehicle.setTransport("PUBLIC");
                } else {
                    vehicle.setTransport("PRIVATE");
                }

                this.vehicleRepository.save(vehicle);
                responseData.put("vehicle", vehicle);
                response.setResponseCode(AppConstants.CREATED);
                response.setResponseMessage(AppConstants.MSG_RESOURCE_UPDATED);
                response.setResponseData(responseData);
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.update() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.update() : {} - end");

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {

        logger.info("in VehicleServiceImpl.delete() : {} - start");

        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Vehicle vehicle = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {

            if (id == null || id == 0) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            }

            vehicle = this.vehicleRepository.findById(id).get();

            if (vehicle.getId() == null) {
                responseData.put("vehicle", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_INVALID_VEHICLE_ID);
                response.setResponseData(responseData);
                return response;
            }

            this.vehicleRepository.deleteById(id);
            responseData.put("vehicle", vehicle);
            response.setResponseCode(AppConstants.OK);
            response.setResponseMessage(AppConstants.MSG_RESOURCE_DELETED);
            response.setResponseData(responseData);

        } catch (NotFoundException ex) {
            logger.error("" + ex);
            logger.error("in VehicleServiceImpl.delete() : {} - error");
            ex.printStackTrace();
        }

        logger.info("in VehicleServiceImpl.delete() : {} - end");

        return response;
    }
}