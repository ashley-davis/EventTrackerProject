package com.skilldistillery.dogwalker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.dogwalker.repositories.DogwalkerRepository;

@Service
public class DogwalkerServiceImpl implements DogwalkerService{
	
	@Autowired
	private DogwalkerRepository repo;
	
	@Override
	public List<Dogwalker> getAllDogwalkers() {
		return null;
	}

	
}
