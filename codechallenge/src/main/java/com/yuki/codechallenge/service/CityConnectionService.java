package com.yuki.codechallenge.service;

import com.yuki.codechallenge.dto.City;

import java.util.List;

public interface CityConnectionService {
    List<City> getAllCity();

    City getCity(String cityName);

    Boolean isTwoCityConnect(String firstCity, String secondCity);
}
