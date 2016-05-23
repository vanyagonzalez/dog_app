package com.piv.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piv.config.AppConfig;
import com.piv.model.Dog;
import com.piv.repositories.DogRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.*;
import static com.jayway.restassured.RestAssured.*;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class DogEndpointTest extends AbstractTestNGSpringContextTests {

    @Test
    public void mustGetValidCollection() throws IOException {
        String dogBody = get("/dog-web/dogs").andReturn().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Dog> dogs = objectMapper.readValue(dogBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Dog.class));
        assertEquals(DogRepository.DOGS.size(), dogs.size());
    }

}