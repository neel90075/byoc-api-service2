package com.learn.java.apihosting;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("1.0")
public class RestController {
    private static final List<Country> COUNTRIES = List.of(
            new Country("United States of America", "Washington D.C.", 339_996_563),
            new Country("China", "Beijing", 1_411_750_000),
            new Country("India", "New Delhi", 1_428_627_663)
    );

    @GetMapping()
    public List<Country> getCountries(){
        return COUNTRIES;
    }

    @GetMapping("/token")
    public Token getAccessToken(){
        return new Token("client_credentials", "Lpl1jZBxxxxxxxjcxfB", "16SA4bbcpwGfxxxxxxxxxxxxxxxxxUdXrI2Ba05BXULgVe3qtfJP9");
    }
    @PostMapping("/token")
    public AccessToken postAccessToken(@RequestBody Token token){
        System.out.println(token);
        if(!token.getGrant_type().isBlank() && token.getGrant_type().equals("client_credentials")
                && !token.getClient_id().isEmpty() && token.getClient_id().equals("Lpl1jZBxxxxxxxjcxfB")
                && !token.getClient_secret().isEmpty() && token.getClient_secret().equals("16SA4bbcpwGfxxxxxxxxxxxxxxxxxUdXrI2Ba05BXULgVe3qtfJP9")) {
            return new AccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkZW1vIiwibmFtZSI6IkRlbW8gTW9kZXJhdG9yIiwiaWF0IjoxNTE2MjM5MDIyfQ.3J4zv");
        } else {
            return new AccessToken("Invalid credentials");
        }
    }

    @GetMapping("/token/{id}")
    public Country getAccessTokenById(@PathVariable int id){
        return new Country("United States of America", "Washington D.C.", id);
    }
}

