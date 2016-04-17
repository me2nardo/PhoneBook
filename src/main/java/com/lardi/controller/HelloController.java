package com.lardi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HelloController {
    
    @RequestMapping("/")
    public String index() {

        return "index";
    }
    
}
