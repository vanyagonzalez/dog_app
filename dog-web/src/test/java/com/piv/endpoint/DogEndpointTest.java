package com.piv.endpoint;

import com.jayway.restassured.http.ContentType;
import com.piv.model.Dog;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static com.jayway.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DogEndpointTest {

    @Test
    public void mustCreateDog() throws IOException {
        Dog[] dogsArray = given().get("/dog-web/dogs").andReturn().as(Dog[].class);
        List<Dog> dogs = new ArrayList<>(Arrays.asList(dogsArray));
        int oldSize = dogs.size();

        Dog newDog = Dog.random();
        newDog = given().body(newDog).contentType(ContentType.JSON).post("/dog-web/dog").andReturn().as(Dog.class);

        dogsArray = given().get("/dog-web/dogs").andReturn().as(Dog[].class);
        dogs = new ArrayList<>(Arrays.asList(dogsArray));
        assertEquals(oldSize + 1, dogs.size());

        assertTrue(dogs.contains(newDog));
    }

}