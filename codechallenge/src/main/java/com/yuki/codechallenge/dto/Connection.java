package com.yuki.codechallenge.dto;

import java.util.*;

public class Connection {
    private final Map<String, List<String>> cityconnection;

    public Connection() {
        cityconnection = new HashMap<>();
    }

    public Map<String, List<String>> getCityconnection() {
        return cityconnection;
    }

    public void addConnection(String firstCity, String secondCity) {
        if (cityconnection.containsKey(firstCity))
            cityconnection.get(firstCity).add(secondCity);
        else
            cityconnection.put(firstCity, new ArrayList<>(Collections.singletonList(secondCity)));
        if (cityconnection.containsKey(secondCity))
            cityconnection.get(secondCity).add(firstCity);
        else
            cityconnection.put(secondCity, new ArrayList<>(Collections.singletonList(firstCity)));
    }
}
