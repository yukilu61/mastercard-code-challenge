package com.yuki.codechallenge.repository.implementation;

import com.yuki.codechallenge.dto.Connection;
import com.yuki.codechallenge.repository.CityConnectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CityConnectionRepositoryImpl implements CityConnectionRepository {

    private final Connection connections;

    /**
     * initialization and read data from txt file
     *
     * @throws IOException if read file failed, throw IOException
     */
    public CityConnectionRepositoryImpl() {
        connections = new Connection();
        try {
            ClassPathResource classPathResource = new ClassPathResource("city.txt");
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String connection;
            while ((connection = reader.readLine()) != null) {
                String[] city = connection.split(",");
                connections.addConnection(city[0], city[1]);
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    /**
     * Find all cities
     *
     * @return all cities
     */
    @Override
    public Map<String, List<String>> getAllCity() {
        return connections.getCityconnection();
    }

}
