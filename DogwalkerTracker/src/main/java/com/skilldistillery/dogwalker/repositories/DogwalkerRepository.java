package com.skilldistillery.dogwalker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dogwalker.model.Walk;

public interface WalkRepository extends JpaRepository<Walk, Long> {
	
}
