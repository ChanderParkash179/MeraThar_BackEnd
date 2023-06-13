package com.mera.thar.back_app.Service;

import com.mera.thar.back_app.Entity.Response;

import java.util.Map;

public interface CityService {

    // get city by Id
    Response getById(Map<String, Object> input);

    // get city by name
    Response getByName(Map<String, Object> input);

    // all cities
    Response getAll();

    // save city
    Response save(Map<String, Object> input);

    // update city
    Response update(Map<String, Object> input);

    // delete city
    Response delete(Map<String, Object> input);

}
