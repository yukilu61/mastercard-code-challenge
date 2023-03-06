package com.yuki.codechallenge.servicetest;

import com.yuki.codechallenge.dto.City;
import com.yuki.codechallenge.repository.CityConnectionRepository;
import com.yuki.codechallenge.service.CityConnectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest
public class CityConnectionServiceTest {
    @Autowired
    private CityConnectionService cityConnectionService;

    @MockBean
    private CityConnectionRepository cityConnectionRepository;

    private Map allcity;

    @BeforeEach
    public void initial() {
        allcity = new HashMap<String, List<String>>();
        City c1 = new City("New York", new ArrayList<>(Arrays.asList("Boston")));
        City c2 = new City("Boston", new ArrayList<>(Arrays.asList("New York")));
        City c3 = new City("Trenton", new ArrayList<>(Arrays.asList("Albany")));
        City c4 = new City("Albany", new ArrayList<>(Arrays.asList("Trenton")));
        allcity.put(c1.getCityName(), c1.getCityConnection());
        allcity.put(c2.getCityName(), c2.getCityConnection());
        allcity.put(c3.getCityName(), c3.getCityConnection());
        allcity.put(c4.getCityName(), c4.getCityConnection());
    }

    @Test
    public void testGetAllCity() {
        Mockito.when(cityConnectionRepository.getAllCity()).thenReturn(allcity);
        List<City> result = cityConnectionService.getAllCity();
        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void testGetExistCity() {
        Mockito.when(cityConnectionRepository.getAllCity()).thenReturn(allcity);
        City result = cityConnectionService.getCity("New York");
        Assertions.assertEquals("New York", result.getCityName());
    }

    @Test
    public void testGetNotExistCity() {
        Mockito.when(cityConnectionRepository.getAllCity()).thenReturn(allcity);
        City result = cityConnectionService.getCity("Newark");
        Assertions.assertNull(result);
    }

    @Test
    public void testTwoCityConnect() {
        Mockito.when(cityConnectionRepository.getAllCity()).thenReturn(allcity);
        Assertions.assertTrue(cityConnectionService.isTwoCityConnect("Boston", "New York"));
    }

    @Test
    public void testTwoCityNotConnect() {
        Mockito.when(cityConnectionRepository.getAllCity()).thenReturn(allcity);
        Assertions.assertFalse(cityConnectionService.isTwoCityConnect("Boston", "Trenton"));
    }
}
