package com.mahallem.Service.Impl;

import com.mahallem.Entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    public void get(){
        Animal animal = new Animal();
        animal.setSex("male");
        animal.setAge("3");
        animal.setType("Golden");
    }
}
