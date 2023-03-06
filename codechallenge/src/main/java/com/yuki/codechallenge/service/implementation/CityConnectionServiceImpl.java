package com.yuki.codechallenge.service.implementation;

import com.yuki.codechallenge.dto.City;
import com.yuki.codechallenge.repository.CityConnectionRepository;
import com.yuki.codechallenge.service.CityConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityConnectionServiceImpl implements CityConnectionService {

    @Autowired
    private CityConnectionRepository cityconnectionRepository;

    @Override
    public List<City> getAllCity() {
        List<City> allCity = new ArrayList<>();
        for (Map.Entry<String, List<String>> item : cityconnectionRepository.getAllCity().entrySet()) {
            allCity.add(new City(item.getKey(), item.getValue()));
        }
        return allCity;
    }

    public City getCity(String cityName) {
        if (cityconnectionRepository.getAllCity().containsKey(cityName))
            return new City(cityName, cityconnectionRepository.getAllCity().get(cityName));
        return null;
    }

    @Override
    public Boolean isTwoCityConnect(String firstCity, String secondCity) {
        List<City> allcity = getAllCity();
        Map<String, Boolean> marked = new HashMap<>();
        for (City city : allcity)
            marked.put(city.getCityName(), false);
        DFS(marked, getCity(firstCity));
        return marked.get(secondCity);
    }

    public void DFS(Map<String, Boolean> marked, City firstCity) {
        marked.put(firstCity.getCityName(), true);
        for (String cn : firstCity.getCityConnection()) {
            if (!marked.get(cn))
                DFS(marked, getCity(cn));
        }
    }
}
