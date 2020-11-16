package com.skilldistillery.dogwalker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalkTest {
	
private static EntityManagerFactory emf;
private EntityManager em;
private Walk cowboy;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf= Persistence.createEntityManagerFactory("DogwalkerPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		cowboy = em.find(Dog.class, 1);
		}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		cowboy = null;
	}

	@Test
	void test() {
		assertNotNull(dog);
		assertEquals("Cowboy", dog.getName());
	}

}
