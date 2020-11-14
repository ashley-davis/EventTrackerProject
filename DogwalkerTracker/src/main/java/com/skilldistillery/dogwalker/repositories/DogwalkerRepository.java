package com.skilldistillery.dogwalker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.dogwalker.entities.Dog;

public interface DogwalkerRepository extends JpaRepository<Dog, Integer>

{

}
