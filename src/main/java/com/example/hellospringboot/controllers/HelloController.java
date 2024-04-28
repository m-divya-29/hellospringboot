package com.example.hellospringboot.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/hello")
public class HelloController {
    //@GetMapping("/name/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }
    //@GetMapping
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
}
