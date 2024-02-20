package com.datpham.foodorder.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superadmin")
@RequiredArgsConstructor
public class SuperadminRestController {


    //RessourceEndPoint:http://localhost:8087/api/superadmin/hi
    @GetMapping("/hi")
    public String sayHi ()
    { return "Hi" ;}


}
