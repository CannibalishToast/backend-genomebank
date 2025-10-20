package com.genomebank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/secure/hello")
    public String helloSecure() {
        return "✅ Acceso autorizado: bienvenido al área protegida.";
    }
}
