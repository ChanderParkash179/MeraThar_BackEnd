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
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TouristPointServiceImpl implements TouristPointService {

    private final TouristPointRepository touristPointRepository;
    private final CityRepository cityRepository;

    @Override
    public Response getAllTouristPointsByCityName(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (input == null) {
                responseData.put("places", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else if (name == null || name.isEmpty()) {
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
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getById(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : null;
        TouristPoint place = null;
        try {
            if (input == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else if (id == null || id == 0) {
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
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getByName(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;

        try {
            if (input == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else if (name == null || name.isEmpty()) {
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
            return response;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response getAll() {
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
            return response;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response save(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String location = (String) input.get("location") != null ? (String) input.get("location") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;
        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        TouristPoint place = null;

        try {
            if (input == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else {
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

                        City getCity = this.cityRepository.findById(city).get() != null ? this.cityRepository.findById(city).get() : null;

                        if (getCity == null) {
                            responseData.put("places", null);
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
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        String name = (String) input.get("name") != null ? (String) input.get("name") : null;
        String location = (String) input.get("location") != null ? (String) input.get("location") : null;
        String description = (String) input.get("description") != null ? (String) input.get("description") : null;
        Integer city = (Integer) input.get("city") != null ? (Integer) input.get("city") : null;

        TouristPoint place = null;
        try {
            if (input == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            }
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

            City getCity = this.cityRepository.findById(city).get();

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
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response delete(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();
        TouristPoint place = null;
        Integer id = (Integer) input.get("id") != 0 ? (Integer) input.get("id") : 0;
        try {
            if (input == null) {
                responseData.put("place", null);
                response.setResponseCode(AppConstants.NOT_FOUND);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            }
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
            return response;
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        }

        return response;
    }
}