package com.skilldistillery.dogwalker;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.projects.dogwalker.model.Walk;
import com.skilldistillery.dogwalker.controllers.WalkController;

@SpringBootTest
class WalkControllerTests {
	
	@Autowired
	private WalkController controller;
	
	@Test
	@Sql("/walks.sql")
	void testGetAllWalks() throws Exception {
		
		// given
		int EXPECTED_NUMBER_OF_WALKS = 4;
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.OK;
		
		// when
		ResponseEntity<List<Walk>> walks = controller.getAllWalks();
		
		// then
		assertThat(walks).isNotNull();
		assertThat(walks.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walks.hasBody()).isTrue();
		assertThat(walks.getBody()).isNotNull();
		assertThat(walks.getBody().size()).isGreaterThanOrEqualTo(EXPECTED_NUMBER_OF_WALKS);
	}

	@Test
	@Sql("/walks.sql")
	void testGetWalkById() throws Exception {
		
		// given
		int ID = 1;
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.OK;
		String EXPECTED_NAME = "Pluto";
		Date EXPECTED_START = new SimpleDateFormat("MMM d, yyyy HH:mm:ss").parse("NOV 12, 2020 10:00:00");
		int EXPECTED_DURATION = 60;
		float EXPECTED_COST = 25.00f;
		boolean EXPECTED_COMPLETED = true;
		boolean EXPECTED_CANCELLED = false;
		
		// when
		ResponseEntity<Walk> walk = controller.getWalkById(ID);
		
		// then
		assertThat(walk).isNotNull();
		assertThat(walk.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walk.hasBody()).isTrue();
		assertThat(walk.getBody()).isNotNull();
		assertThat(walk.getBody().getId()).isEqualTo(ID);
		assertThat(walk.getBody().getName()).isEqualTo(EXPECTED_NAME);
		assertThat(walk.getBody().getStart()).isEqualTo(EXPECTED_START);
		assertThat(walk.getBody().getDuration()).isEqualTo(EXPECTED_DURATION);
		assertThat(walk.getBody().getCost()).isEqualTo(EXPECTED_COST);
		assertThat(walk.getBody().getCompleted()).isEqualTo(EXPECTED_COMPLETED);
		assertThat(walk.getBody().getCancelled()).isEqualTo(EXPECTED_CANCELLED);
	}
	
	@Test
	@Sql("/walks.sql")
	void testGetWalkById_ShouldFail() throws Exception {
		
		// given
		int ID = 999;
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.NOT_FOUND;
		
		// when
		ResponseEntity<Walk> walk = controller.getWalkById(ID);
		
		// then
		assertThat(walk).isNotNull();
		assertThat(walk.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
	}

	@Test
	@Sql("/walks.sql")
	void testCreateWalk() throws Exception {
		
		// given
		String NEW_NAME = "Goofy";
		int NEW_DURATION = 75;
		float NEW_COST = 40.00f;
		Date NEW_START = new SimpleDateFormat("MMM d, yyyy HH:mm:ss").parse("NOV 20, 2020 08:00:00"); 
		
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.CREATED;
		Date EXPECTED_START = NEW_START;
		String EXPECTED_NAME = NEW_NAME;
		int EXPECTED_DURATION = NEW_DURATION;
		float EXPECTED_COST = NEW_COST;
		boolean EXPECTED_COMPLETED = false;
		boolean EXPECTED_CANCELLED = false;
		
		// when
		Walk walk = new Walk(NEW_NAME, NEW_START, NEW_DURATION, NEW_COST);
		ResponseEntity<Walk> walk2 = controller.createWalk(walk);
		
		// then
		assertThat(walk2).isNotNull();
		assertThat(walk2.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walk2.hasBody()).isTrue();
		assertThat(walk2.getBody()).isNotNull();
		assertThat(walk2.getBody().getId()).isGreaterThan(0);
		assertThat(walk2.getBody().getName()).isEqualTo(EXPECTED_NAME);
		assertThat(walk2.getBody().getStart()).isEqualTo(EXPECTED_START);
		assertThat(walk2.getBody().getDuration()).isEqualTo(EXPECTED_DURATION);
		assertThat(walk2.getBody().getCost()).isEqualTo(EXPECTED_COST);
		assertThat(walk2.getBody().getCompleted()).isEqualTo(EXPECTED_COMPLETED);
		assertThat(walk2.getBody().getCancelled()).isEqualTo(EXPECTED_CANCELLED);
	}

	@Test
	@Sql("/walks.sql")
	void testDeleteWalk() throws Exception {
		
		// given
		int ID = 2;
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.NO_CONTENT;
		
		// when
		ResponseEntity<HttpStatus> result = controller.deleteWalk(ID);
		
		// then
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
	}
	
	@Test
	@Sql("/walks.sql")
	void testDeleteWalk_ShouldFail() throws Exception {
		
		// given
		int ID = 999;
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.NOT_FOUND;
		
		// when
		ResponseEntity<HttpStatus> result = controller.deleteWalk(ID);
		
		// then
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
	}

	@Test
	@Sql("/walks.sql")
	void testDeleteAllWalks() throws Exception {
		
		// given
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.NO_CONTENT;
		
		// when
		ResponseEntity<HttpStatus> result = controller.deleteAllWalks();
		ResponseEntity<List<Walk>> walks = controller.getAllWalks();
		
		// then
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walks).isNotNull();
		assertThat(walks.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
	}

	@Test
	@Sql("/walks.sql")
	void testUpdateWalk() throws Exception {
		
		// given
		int ID = 1;
		String NEW_NAME = "Goofy"; // was "Pluto"
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.OK;
		String EXPECTED_NAME = "Goofy";
		int EXPECTED_DURATION = 60;
		float EXPECTED_COST = 25.00f;
		boolean EXPECTED_COMPLETED = true;
		boolean EXPECTED_CANCELLED = false;
		
		// when
		Walk walk = controller.getWalkById(ID).getBody();
		walk.setName(NEW_NAME);
		ResponseEntity<Walk> walk2 = controller.updateWalk(ID, walk);
		
		// then
		assertThat(walk2).isNotNull();
		assertThat(walk2.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walk2.hasBody()).isTrue();
		assertThat(walk2.getBody()).isNotNull();
		assertThat(walk2.getBody().getId()).isEqualTo(ID);
		assertThat(walk2.getBody().getName()).isEqualTo(EXPECTED_NAME);
		assertThat(walk2.getBody().getDuration()).isEqualTo(EXPECTED_DURATION);
		assertThat(walk2.getBody().getCost()).isEqualTo(EXPECTED_COST);
		assertThat(walk2.getBody().getCompleted()).isEqualTo(EXPECTED_COMPLETED);
		assertThat(walk2.getBody().getCancelled()).isEqualTo(EXPECTED_CANCELLED);
	}

	@Test
	@Sql("/walks.sql")
	void testPartiallyUpdateWalk() throws Exception {
		
		// given
		int ID = 1;
		String NEW_NAME = "Goofy"; // was "Pluto"
		HttpStatus EXPECTED_STATUS_CODE = HttpStatus.OK;
		String EXPECTED_NAME = "Goofy";
		int EXPECTED_DURATION = 60;
		float EXPECTED_COST = 25.00f;
		boolean EXPECTED_COMPLETED = true;
		boolean EXPECTED_CANCELLED = false;
		
		// when
		Walk walk = new Walk(NEW_NAME, null, 0, -1);
		ResponseEntity<Walk> walk2 = controller.partiallyUpdateWalk(ID, walk);
		
		// then
		assertThat(walk2).isNotNull();
		assertThat(walk2.getStatusCode()).isEqualTo(EXPECTED_STATUS_CODE);
		assertThat(walk2.hasBody()).isTrue();
		assertThat(walk2.getBody()).isNotNull();
		assertThat(walk2.getBody().getId()).isEqualTo(ID);
		assertThat(walk2.getBody().getName()).isEqualTo(EXPECTED_NAME);
		assertThat(walk2.getBody().getDuration()).isEqualTo(EXPECTED_DURATION);
		assertThat(walk2.getBody().getCost()).isEqualTo(EXPECTED_COST);
		assertThat(walk2.getBody().getCompleted()).isEqualTo(EXPECTED_COMPLETED);
		assertThat(walk2.getBody().getCancelled()).isEqualTo(EXPECTED_CANCELLED);
	}
}
