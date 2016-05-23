package com.piv.endpoint;

import com.piv.config.AppConfig;
import com.piv.services.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class DogEndpointJunitTest {
    @Test
    public void test() {
        String dogBody = given().get("/dog-web/dogs").andReturn().asString();
        String str = get("/dog-web/dogs").andReturn().asString();
        System.out.println(str);

    }

    @Autowired
    private DogService dogService;
    @Test
    public void test2() {
        System.out.println(dogService.getAllDogs());
    }
}
