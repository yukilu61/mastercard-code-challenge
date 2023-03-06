package com.yuki.codechallenge.controller;

import com.yuki.codechallenge.service.CityConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/connection")
@Slf4j
public class ConnectionController {
    @Autowired
    private CityConnectionService cityConnectionService;

    @GetMapping("/connected")
    public String isTwoCityConnect(@RequestParam("origin") String originName, @RequestParam("destination") String destinationName) {
        return cityConnectionService.isTwoCityConnect(originName, destinationName) ? "yes" : "no";
    }

}
