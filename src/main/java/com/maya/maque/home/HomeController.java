package com.maya.maque.home;

import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private ConfigurationProvider configurationProvider;

    @GetMapping("/checkpreload.htm")
    public String checkPreload() {
        return "success";
    }

    @GetMapping("/")
    public String homepage() {
        return configurationProvider.getProperty("greeting",String.class);
    }


}
