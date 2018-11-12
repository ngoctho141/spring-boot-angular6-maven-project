package com.swathisprasad.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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