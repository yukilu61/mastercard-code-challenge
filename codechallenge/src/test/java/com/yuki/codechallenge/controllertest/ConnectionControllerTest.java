package com.yuki.codechallenge.controllertest;

import com.yuki.codechallenge.dto.City;
import com.yuki.codechallenge.service.CityConnectionService;
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


@AutoConfigureMockMvc
@SpringBootTest
public class ConnectionControllerTest {
    @MockBean
    private CityConnectionService cityConnectionService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIsConnect() throws Exception {
        Mockito.when(cityConnectionService.getCity("Newark")).thenReturn(new City("Newark", null));
        Mockito.when(cityConnectionService.getCity("Boston")).thenReturn(new City("Boston", null));
        Mockito.when(cityConnectionService.isTwoCityConnect("Newark", "Boston")).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/connection/connected?origin=Newark&destination=Boston");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("yes"));
    }

    @Test
    public void testNotConnect() throws Exception {
        Mockito.when(cityConnectionService.getCity("Newark")).thenReturn(new City("Newark", null));
        Mockito.when(cityConnectionService.getCity("Boston")).thenReturn(new City("Boston", null));
        Mockito.when(cityConnectionService.isTwoCityConnect("Newark", "Boston")).thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/connection/connected?origin=Newark&destination=Boston");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("no"));
    }

    @Test
    public void testCityNotFound() throws Exception {
        Mockito.when(cityConnectionService.getCity("Newark")).thenReturn(new City("Newark", null));
        Mockito.when(cityConnectionService.getCity("Boston")).thenReturn(null);
        Mockito.when(cityConnectionService.isTwoCityConnect("Newark", "Boston")).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/connection/connected?origin=Newark&destination=Boston");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.content().string("city not found"));
    }
}
