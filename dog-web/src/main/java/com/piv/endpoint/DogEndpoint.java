package com.piv.endpoint;

import com.piv.model.Dog;
import com.piv.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DogEndpoint {
    @Autowired
    private DogRepository dogRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/dogs")
    public List<Dog> getAllDogs() {
        return dogRepository.getAllDogs();
    }
}
