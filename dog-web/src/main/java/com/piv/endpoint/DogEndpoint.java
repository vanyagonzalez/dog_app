package com.piv.endpoint;

import com.piv.model.Dog;
import com.piv.services.DogService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DogEndpoint {

    private DogService dogService;

    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dogs")
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }
}
