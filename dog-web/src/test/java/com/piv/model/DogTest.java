package com.piv.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.testng.Assert.*;

@Test
public class DogTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldFailIfNameIsEmpty() {
        Dog dog = Dog.random();
        dog.setName("");

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(dog);

        assertEquals(1, constraintViolations.size());
        assertEquals(Dog.NAME_MSG, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailIfNameIsLarge() {
        Dog dog = Dog.random();
        dog.setName(RandomStringUtils.random(Dog.MAX_LEN + 1));

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(dog);

        assertEquals(1, constraintViolations.size());
        assertEquals(Dog.NAME_MSG, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailIfDateIsNotInPast() {
        Dog dog = Dog.random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        dog.setDateOfBirth(calendar.getTime());

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(dog);

        assertEquals(1, constraintViolations.size());
        assertEquals(Dog.DATE_OF_BIRTH_MSG, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailIfHeightIsNegative() {
        Dog dog = Dog.random();
        dog.setHeight(-dog.getHeight());

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(dog);

        assertEquals(1, constraintViolations.size());
        assertEquals(Dog.HEIGHT_MSG, constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailIfWeightIsNegative() {
        Dog dog = Dog.random();
        dog.setWeight(-dog.getWeight());

        Set<ConstraintViolation<Dog>> constraintViolations = validator.validate(dog);

        assertEquals(1, constraintViolations.size());
        assertEquals(Dog.WEIGHT_MSG, constraintViolations.iterator().next().getMessage());
    }
}