package com.skilldistillery.dogwalker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.dogwalker.entities.Walk;

import com.skilldistillery.dogwalker.services.DogwalkerService;

@CrossOrigin(origins = "http://localhost:8086")
@RestController
public class DogwalkerController {

	@Autowired
    DogwalkerService dogwalkerService;
	
	@GetMapping("/walks")
	public ResponseEntity<List<Walk>> getAllWalks() {
		
	    try {
	      
	    	List<Walk> walks = dogwalkerService.getAllWalks();
	    	
	    	if (walks == null || walks.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}
	
	    	return new ResponseEntity<>(walks, HttpStatus.OK);
	    	
	    } catch (Exception e) {
	      
	    	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/walks/{id}")
	public ResponseEntity<Walk> getWalkById(@PathVariable("id") long id) {
		
		try {
	    	
			Walk walk = dogwalkerService.getWalkById(id);
		    if (walk != null) {
		    	
		    	return new ResponseEntity<>(walk, HttpStatus.OK);
		    
		    } else {
		      
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	      
	    } catch (Exception e) {
	    	
	       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@PostMapping("/walks")
	public ResponseEntity<Walk> createWalk(@RequestBody Walk walk) {
		
	    try {
	    	
	      Walk _walk = dogwalkerService.createWalk(walk);
	      
	      return new ResponseEntity<>(_walk, HttpStatus.CREATED);
	      
	    } catch (Exception e) {
	    	
	       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/walks/{id}")
	public ResponseEntity<HttpStatus> deleteWalk(@PathVariable("id") long id) {
		
	    try {
	    	
	    	if (dogwalkerService.walkExists(id)) {
	    		
	    		dogwalkerService.deleteWalkById(id);
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    
		    } else {
		      
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	      
	    } catch (Exception e) {
	    	
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@DeleteMapping("/walks")
	public ResponseEntity<HttpStatus> deleteAllWalks() {
		  
	    try {
	    	
	    	dogwalkerService.deleteAllWalks();
	    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	
	    } catch (Exception e) {
	    	
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	}
	
	@PutMapping("/walks/{id}")
	public ResponseEntity<Walk> updateWalk(@PathVariable("id") long id, @RequestBody Walk walk) {
	
		 try {
		
			 if (dogwalkerService.walkExists(id)) {
			    	
		    	return new ResponseEntity<>(dogwalkerService.updateWalk(id, walk), HttpStatus.OK);
			      
		     } else {
		    	
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		     }
	    
		 } catch (Exception e) {
		    	
		    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
	
	@PatchMapping("/walks/{id}")
	public ResponseEntity<Walk> partiallyUpdateWalk(@PathVariable("id") long id, @RequestBody Walk walk) {
	
		try {
			
			if (dogwalkerService.walkExists(id)) {
		    	
		    	return new ResponseEntity<>(dogwalkerService.partiallyUpdateWalk(id, walk), HttpStatus.OK);
		      
		    } else {
		    	
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	    
		 } catch (Exception e) {
		    	
		    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 }				
	}
}