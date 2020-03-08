package com.maya.maque.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/checkpreload.htm")
    public String checkPreload() {
        return "success";
    }

    @GetMapping("/")
    public String homepage() {
        return "Hello World !";
    }


}
