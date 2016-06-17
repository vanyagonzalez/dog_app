package com.piv.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piv.model.Dog;
import com.piv.repositories.DogRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
public class DogMockMvcEndpointTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeClass
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void test() throws Exception {
        String dogBody = mvc.perform(get("/dogs")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Dog> dogs = objectMapper.readValue(dogBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Dog.class));
        assertEquals(DogRepositoryInMemory.DOGS.size(), dogs.size());
    }
}
