package com.piv.repositories;

import com.piv.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
@Transactional
public class DogRepositoryHibernateTest extends AbstractTransactionalTestNGSpringContextTests {

    private int beforeDogNum;
    @Autowired
    private WebApplicationContext context;

    private DogRepositoryInterface dogRepositoryInterface;

    @BeforeMethod
    public void before() {
        dogRepositoryInterface = (DogRepositoryInterface) context.getBean("dogRepositoryHibernate");
        if (beforeDogNum == 0) {
            beforeDogNum = dogRepositoryInterface.getAllDogs().size();
        } else {
            int afterDogNum =  dogRepositoryInterface.getAllDogs().size();
            assertEquals(beforeDogNum, afterDogNum);
        }
    }

    @Test
    public void testFirstCreateDog() throws Exception {
        createNewDog();
    }

    @Test
    public void testSecondCreateDog() throws Exception {
        createNewDog();
    }

    private void createNewDog() {
        dogRepositoryInterface.createDog(Dog.random());
        int dogNum = dogRepositoryInterface.getAllDogs().size();
        assertEquals(beforeDogNum, dogNum - 1);
    }
}