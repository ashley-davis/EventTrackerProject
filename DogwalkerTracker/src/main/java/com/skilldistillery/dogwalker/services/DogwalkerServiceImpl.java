package com.skilldistillery.dogwalker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.dogwalker.repositories.WalkRepository;

@Service
public class WalkServiceImpl implements WalkService {

	@Autowired
	WalkRepository walkRepository;
	
	@Override
	public boolean walkExists(long id) {
		
		Optional<Walk> walk = walkRepository.findById(id);
		
		return walk.isPresent();
	}
	
	@Override
    public List<Walk> getAllWalks() {
        
		List<Walk> walks = new ArrayList<Walk>();
    	walkRepository.findAll().forEach(walks::add);    	
    	
    	return walks;
    }

	@Override
	public Walk getWalkById(long id) {
		
		Optional<Walk> walk = walkRepository.findById(id);
		
		return walk.isPresent() ? walk.get() : null;
	}

	@Override
	public void deleteWalkById(long id) {
		
		walkRepository.deleteById(id);
	}

	@Override
	public void deleteAllWalks() {
		
		walkRepository.deleteAll();
	}

	@Override
	public Walk createWalk(Walk walk) {
	
		return walkRepository.save(walk);
	}

	@Override
	public Walk updateWalk(long id, Walk walk) {
		
		Walk updatedWalk = getWalkById(id);
		
    	updatedWalk.setName(walk.getName());
    	updatedWalk.setStart(walk.getStart());
    	updatedWalk.setDuration(walk.getDuration());
    	updatedWalk.setCost(walk.getCost());
    	updatedWalk.setCompleted(walk.getCompleted());
    	updatedWalk.setCancelled(walk.getCancelled());
    	
      	return walkRepository.save(updatedWalk);
	}

	@Override
	public Walk partiallyUpdateWalk(long id, Walk walk) {
		
		Walk updatedWalk = getWalkById(id);
		
    	if (walk.getName() != null) updatedWalk.setName(walk.getName());
    	if (walk.getStart() != null) updatedWalk.setStart(walk.getStart());
    	if (walk.getDuration() > 0) updatedWalk.setDuration(walk.getDuration());
    	if (walk.getCost() >= 0) updatedWalk.setCost(walk.getCost());
    	if (walk.isCompletedSet()) updatedWalk.setCompleted(walk.getCompleted());
    	if (walk.isCancelledSet()) updatedWalk.setCancelled(walk.getCancelled());
    	
      	return walkRepository.save(updatedWalk);		
	}	
}