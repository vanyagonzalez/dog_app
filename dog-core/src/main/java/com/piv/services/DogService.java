package com.piv.services;

import com.piv.model.Dog;
import com.piv.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs() {
        return dogRepository.getAllDogs();
    }
}
