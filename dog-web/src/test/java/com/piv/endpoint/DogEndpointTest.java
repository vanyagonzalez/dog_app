package com.piv.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piv.model.Dog;
import com.piv.repositories.DogRepository;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class DogEndpointTest {

    @Test
    public void mustGetValidCollection() throws IOException {
        String dogBody = get("/dog-web/dogs").andReturn().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Dog> dogs = objectMapper.readValue(dogBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Dog.class));
        assertEquals(DogRepository.DOGS.size(), dogs.size());
    }

}