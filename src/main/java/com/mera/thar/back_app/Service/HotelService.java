package com.mera.thar.back_app.Service;

import com.mera.thar.back_app.Entity.Response;

import java.util.Map;

public interface HotelService {

    Response getAllHotelsByCityName(Map<String, Object> input);

    Response getById(Map<String, Object> input);

    Response getByName(Map<String, Object> input);

    Response getAll();

    Response save(Map<String, Object> input);

    Response update(Map<String, Object> input);

    Response delete(Map<String, Object> input);

}
