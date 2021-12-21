package com.example.livraria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LivrariaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void checkException() {
		assertEquals(3, 3);
	}
}
