package com.piv.repositories;

import com.piv.model.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DogRepository {
    public static final List<Dog> DOGS = new ArrayList<Dog>();
    static {
        DOGS.add(new Dog("1", new Date(), 1d, 1d));
        DOGS.add(new Dog("2", new Date(), 2d, 2d));
        DOGS.add(new Dog("3", new Date(), 3d, 3d));
    }

    public List<Dog> getAllDogs() {
        return DOGS;
    }
}
