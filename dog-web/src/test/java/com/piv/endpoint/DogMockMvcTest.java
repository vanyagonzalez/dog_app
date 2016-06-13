package com.piv.endpoint;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
public class DogMockMvcTest extends AbstractTestNGSpringContextTests {
}
