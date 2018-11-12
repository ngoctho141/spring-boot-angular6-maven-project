package com.swathisprasad.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Swathi
 *
 */
@Controller
public class ViewController {

    @CrossOrigin("https://car-app141.herokuapp.com")
    @GetMapping("/car-list")
    public String home() {
        return "forward:/index.html";
    }

}