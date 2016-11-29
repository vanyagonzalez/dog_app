package com.piv.repositories;

import com.piv.model.Dog;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

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

    @Test
    public void testUpdate() {
        List<Dog> allDogs = dogRepositoryInterface.getAllDogs();
        Dog dog = allDogs.get(RandomUtils.nextInt(0, allDogs.size()));
        String newName = RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10));
        dog.setName(newName);
        dogRepositoryInterface.updateDog(dog);
        Dog updated = dogRepositoryInterface.getById(dog.getDogId());
        assertEquals(newName, updated.getName());
    }

    @Test
    public void testRollback() {
        List<Dog> allDogs = dogRepositoryInterface.getAllDogs();
        Dog dog = allDogs.get(RandomUtils.nextInt(0, allDogs.size()));
        String newName = RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10));
        dog.setName(newName);
        dogRepositoryInterface.updateDog(dog);

        TestTransaction.flagForRollback();
        TestTransaction.end();

        Dog updated = dogRepositoryInterface.getById(dog.getDogId());
        assertNotEquals(newName, updated.getName());
    }
}