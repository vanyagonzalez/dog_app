package com.piv.repositories;

import com.piv.model.Dog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DogRepositoryInMemory implements DogRepositoryInterface {
    public static final List<Dog> DOGS = new ArrayList<Dog>();

    @Override
    public List<Dog> getAllDogs() {
        return DOGS;
    }

    @Override
    public Dog getById(Long dogId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Dog createDog(Dog dog) {
        synchronized (DOGS) {
            dog.setDogId((long)(DOGS.size() + 1));
            DOGS.add(dog);
        }
        return dog;
    }

    @Override
    public Dog updateDog(Dog dog) {
        throw new UnsupportedOperationException();
    }
}
