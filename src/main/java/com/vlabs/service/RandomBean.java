package com.vlabs.service;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class RandomBean {
 
 
    private final Random random;
 
 
    public RandomBean() {
        random = new Random();
    }
 
 
    
    public String random() {
        return "Random " + random.nextInt();
    }
}