package vn.techzen.BaseAPI.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingController {
    @CrossOrigin(origins = "http://localhost:5173/")
    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = " ") String name) {
        return "Hello " + name + "!";
    }
}
