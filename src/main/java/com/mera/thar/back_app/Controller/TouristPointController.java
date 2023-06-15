package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.TouristPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/city/tourist-point/")
@RestController
@RequiredArgsConstructor
public class TouristPointController {

    private final TouristPointService touristPointService;

}
