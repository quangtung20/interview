package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	@Test
	public void contextLoads() {
		Integer a = 5;
		assertEquals(a, 5);
		System.out.println("test done");
	}

}
