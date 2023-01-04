package com.ims.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/{key}")
    public String HelloKey(@PathVariable("key") String key){
        return "Hello " + key;
    }
}
