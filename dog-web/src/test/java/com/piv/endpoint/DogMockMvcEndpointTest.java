package com.piv.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piv.model.Dog;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
    public void shouldReturnInitDogs() throws Exception {
        List<Dog> dogs = getDogs();
        assertTrue(!CollectionUtils.isEmpty(dogs));
    }

    @Test
    public void shouldCreateDog() throws Exception {
        List<Dog> oldDogs = getDogs();

        String createdDog = mvc.perform(post("/dog")
                .content(asJsonString(Dog.random()))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse().getContentAsString();

        List<Dog> newDogs = getDogs();

        assertEquals(oldDogs.size() + 1, newDogs.size());
    }

    @Test
    public void shouldRollbackWithNegativeHeight() throws Exception {
        List<Dog> oldDogs = getDogs();

        Dog dog = Dog.random();
        dog.setHeight(-1 * dog.getHeight());

        mvc.perform(post("/dog")
                        .content(asJsonString(dog))
                        .contentType(MediaType.APPLICATION_JSON));

        List<Dog> newDogs = getDogs();

        assertEquals(oldDogs.size(), newDogs.size());
    }

    @Test
    @Ignore
    public void shouldNotRollbackWithNegativeWeight() throws Exception {
        List<Dog> oldDogs = getDogs();

        Dog dog = Dog.random();
        dog.setWeight(-1 * dog.getWeight());

        mvc.perform(post("/dog")
                .content(asJsonString(dog))
                .contentType(MediaType.APPLICATION_JSON));

        List<Dog> newDogs = getDogs();

        assertEquals(oldDogs.size() + 1, newDogs.size());
    }

    private List<Dog> getDogs() throws Exception {
        String dogBody = mvc.perform(get("/dogs")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(dogBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Dog.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
