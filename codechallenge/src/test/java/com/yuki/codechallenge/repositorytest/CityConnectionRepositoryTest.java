package com.yuki.codechallenge.repositorytest;

import com.yuki.codechallenge.repository.CityConnectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityConnectionRepositoryTest {
    @Autowired
    private CityConnectionRepository cityConnectionRepository;

    @Test
    public void testGetAllCity() {
        Assertions.assertEquals(6, cityConnectionRepository.getAllCity().size());
    }
}
