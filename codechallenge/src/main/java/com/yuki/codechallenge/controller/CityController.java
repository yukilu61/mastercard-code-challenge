package com.yuki.codechallenge.controller;

import com.yuki.codechallenge.dto.City;
import com.yuki.codechallenge.service.CityConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@Slf4j
public class CityController {
    @Autowired
    private CityConnectionService cityConnectionService;

    @GetMapping("/allcity")
    public List<City> getAllCity() {
        return cityConnectionService.getAllCity();
    }

    @GetMapping("/{cityname}")
    public City getCity(@PathVariable("cityname") String cityName) {
        City city = cityConnectionService.getCity(cityName);
        if (city == null) {
            log.error("city: " + cityName + " not found");
            throw new RuntimeException("city" + cityName + " not found");
        }
        return city;
    }
}
