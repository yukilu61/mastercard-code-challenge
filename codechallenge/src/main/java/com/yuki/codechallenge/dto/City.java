package com.yuki.codechallenge.dto;

import java.util.List;

public class City {
    String cityName;
    List<String> cityConnection;

    public City(String _cityName, List<String> _cityConnection) {
        this.cityName = _cityName;
        this.cityConnection = _cityConnection;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getCityConnection() {
        return cityConnection;
    }

    public void setCityConnection(List<String> cityConnection) {
        this.cityConnection = cityConnection;
    }
}
