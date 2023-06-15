package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Repository.TouristPointRepository;
import com.mera.thar.back_app.Service.TouristPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TouristPointServiceImpl implements TouristPointService {

    private final TouristPointRepository touristPointRepository;
}