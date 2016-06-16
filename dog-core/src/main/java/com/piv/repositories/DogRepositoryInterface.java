package com.piv.repositories;

import com.piv.model.Dog;

import java.util.List;

/**
 * Created by Ivan_Pavlyukovets on 6/15/2016.
 */
public interface DogRepositoryInterface {
    List<Dog> getAllDogs();

    Dog createDog(Dog dog);
}
