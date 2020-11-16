package com.skilldistillery.dogwalker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.dogwalker.entities.Walk;


public interface DogwalkerRepository extends JpaRepository<Walk, Long> {
	
}
