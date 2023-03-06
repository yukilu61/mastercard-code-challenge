package com.yuki.codechallenge.controllertest;

import com.yuki.codechallenge.dto.City;
import com.yuki.codechallenge.service.CityConnectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class CityControllerTest {
    @MockBean
    private CityConnectionService cityConnectionService;
    @Autowired
    private MockMvc mockMvc;

    private List<City> cities;

    @BeforeEach
    public void createTestData() {
        cities = new ArrayList<>();
        City c1 = new City("New York", new ArrayList<>(Arrays.asList("Boston")));
        City c2 = new City("Newark", new ArrayList<>(Arrays.asList("Philadelphia", "Boston")));
        cities.add(c1);
        cities.add(c2);
    }

    @Test
    public void testGetAllCity() throws Exception {
        Mockito.when(cityConnectionService.getAllCity()).thenReturn(cities);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/city/allcity");
        String expect = "[{\"cityName\":\"New York\",\"cityConnection\":[\"Boston\"]},{\"cityName\":\"Newark\",\"cityConnection\":[\"Philadelphia\",\"Boston\"]}]";
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(expect));
    }

    @Test
    public void testGetExistCity() throws Exception {
        City c = new City("Newark", new ArrayList<>(Arrays.asList("Boston")));
        Mockito.when(cityConnectionService.getCity("Newark")).thenReturn(c);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/city/Newark");
        String expect = "{\"cityName\":\"Newark\",\"cityConnection\":[\"Boston\"]}";
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(expect));
    }

    @Test
    public void testGetNotExistCity() {
        Throwable exception = Assertions.assertThrows(NestedServletException.class, () -> {
            Mockito.when(cityConnectionService.getCity("abc")).thenReturn(null);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/city/abc");
            mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isInternalServerError());
        });
        Assertions.assertEquals("Request processing failed; nested exception is java.lang.RuntimeException: city: abc not found", exception.getMessage());
    }

}
