package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Sreenivasulu on 5/20/2017.
 */
@Controller
public class HelloWorldController {

    @GetMapping("/hello/{user}")
    @ResponseBody
    public String sayHello(@PathVariable String user){
        return "Hello "+ user;
    }
}
