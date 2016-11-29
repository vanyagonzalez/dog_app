package com.piv.repositories;

import com.piv.model.Dog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ivan_Pavlyukovets on 6/15/2016.
 */
public interface DogRepositoryInterface {
    List<Dog> getAllDogs();

    Dog getById(Long dogId);

    Dog createDog(Dog dog);

    @Transactional
    Dog updateDog(Dog dog);
}
