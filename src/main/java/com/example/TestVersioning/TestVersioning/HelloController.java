package com.example.TestVersioning.TestVersioning;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String hello(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }
}
