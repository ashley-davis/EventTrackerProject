package com.skilldistillery.dogwalker.services;

import java.util.List;

import com.skilldistillery.dogwalker.entities.Walk;

public interface DogwalkerService {
	
	boolean walkExists(long id);
	List<Walk> getAllWalks();
	Walk getWalkById(long id);
	void deleteWalkById(long id);
	void deleteAllWalks();
	Walk createWalk(Walk walk);
	Walk updateWalk(long id, Walk walk);
	Walk partiallyUpdateWalk(long id, Walk walk);
}