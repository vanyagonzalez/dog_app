package com.piv.services;

import com.piv.model.Dog;
import com.piv.repositories.DogRepositoryInMemory;
import com.piv.repositories.DogRepositoryInterface;

import java.util.Date;
import java.util.List;

public class DogService {
    private DogRepositoryInterface dogRepositoryInterface;

    public void setDogRepositoryInterface(DogRepositoryInterface dogRepositoryInterface) {
        this.dogRepositoryInterface = dogRepositoryInterface;
    }

    public List<Dog> getAllDogs() {
        return dogRepositoryInterface.getAllDogs();
    }

    public Dog createDog(Dog dog) {
        return dogRepositoryInterface.createDog(dog);
    }

    public void init() {
        for(int i = 1; i < 11; i++) {
            createDog(Dog.random());
        }
    }
}
