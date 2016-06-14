package com.piv.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.piv.model.Dog;
import com.piv.repositories.DogRepository;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DogEndpointTest {
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void mustCreateDog() throws IOException {
        String dogsBody = given().get("/dog-web/dogs").andReturn().asString();
        List<Dog> dogs = OBJECT_MAPPER.readValue(dogsBody, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Dog.class));
        int oldSize = dogs.size();

        Dog newDog = new Dog("4", new Date(), 4d, 4d);
        String body = OBJECT_MAPPER.writeValueAsString(newDog);
        given().body(body).contentType(ContentType.JSON).post("/dog-web/dog");

        dogsBody = given().get("/dog-web/dogs").andReturn().asString();
        dogs = OBJECT_MAPPER.readValue(dogsBody, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Dog.class));
        assertEquals(oldSize + 1, dogs.size());

        assertTrue(dogs.contains(newDog));
    }

}