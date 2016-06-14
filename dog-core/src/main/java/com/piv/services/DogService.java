package com.piv.services;

import com.piv.model.Dog;
import com.piv.repositories.DogRepository;

import java.util.List;

public class DogService {
    private DogRepository dogRepository;

    public void setDogRepository(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.getAllDogs();
    }

    public Dog createDog(Dog dog) {
        return dogRepository.createDog(dog);
    }
}
