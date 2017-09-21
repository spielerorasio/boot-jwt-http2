package com.orasio.example.boot.jwt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    @RequestMapping("/users")
    @ResponseBody
    public String getUsers() {
        return "{\"users\":" +
                "[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]" +
                "}";
    }
}