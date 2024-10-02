package application;

import static org.junit.Assert.assertEquals;
import java.io.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {

	TownGraphManager map;
	String[] town;
	@BeforeEach
	void setUp() throws Exception {
		
		map = new TownGraphManager();
		town = new String[6];
		for(int i = 1; i < 6; i++)
		{
			town[i] = "Town_" + i;
			map.addTown(town[i]);
		}
		map.addRoad(town[1], town[2], 2, "Road_12");
		map.addRoad(town[1], town[5], 10, "Road_15");
		map.addRoad(town[2], town[5], 6, "Road_25");
		map.addRoad(town[2], town[3], 3, "Road_23");
		map.addRoad(town[3], town[4], 3, "Road_34");
	}

	@AfterEach
	void tearDown() throws Exception {
		map = null;
	}

	@Test
	void testAddRoad() {
		ArrayList<String> roads = map.allRoads();
		assertEquals("Road_12", roads.get(0));
		assertEquals("Road_15", roads.get(1));
		assertEquals("Road_23", roads.get(2));
		assertEquals("Road_25", roads.get(3));	
	}

	@Test
	void testGetRoad() {
		assertEquals("Road_12", map.getRoad(town[1], town[2]));
		assertEquals("Road_12", map.getRoad(town[2], town[1]));
	}

	@Test
	void testAddTown() {
		assertEquals(false, map.containsTown("MD"));
		map.addTown("MD");
		assertEquals(true, map.containsTown("MD"));
	}

	@Test
	void testGetTown() {
		assertEquals(new Town("Town_1"), map.getTown("Town_1"));
	}

	@Test
	void testContainsTown() {
		assertEquals(false, map.containsTown("MD"));
		map.addTown("MD");
		assertEquals(true, map.containsTown("MD"));
		map.deleteTown("MD");
		assertEquals(false, map.containsTown("MD"));
	}

	@Test
	void testContainsRoadConnection() {
		assertEquals(true, map.containsRoadConnection(town[1], town[5]));
		assertEquals(false, map.containsRoadConnection(town[3], town[5]));
	}

	@Test
	void testAllRoads() {
		ArrayList<String> roads = map.allRoads();
		assertEquals("Road_12", roads.get(0));
		assertEquals("Road_15", roads.get(1));
		assertEquals("Road_23", roads.get(2));
		assertEquals("Road_25", roads.get(3));
		assertEquals("Road_34", roads.get(4));
		assertTrue(roads.size()==5);
	}

	@Test
	void testDeleteRoadConnection() {
		assertEquals(true, map.containsRoadConnection(town[1], town[5]));
		map.deleteRoadConnection(town[1], town[5], "Road_15");
		assertEquals(false, map.containsRoadConnection(town[1], town[5]));
	}

	@Test
	void testDeleteTown() {
		assertEquals(false, map.containsTown("MD"));
		map.addTown("MD");
		assertEquals(true, map.containsTown("MD"));
		map.deleteTown("MD");
		assertEquals(false, map.containsTown("MD"));
	}

	@Test
	void testAllTowns() {
		ArrayList<String> town = map.allTowns();
		assertEquals("Town_1", town.get(0));
		assertEquals("Town_2", town.get(1));
		assertEquals("Town_3", town.get(2));
		assertEquals("Town_4", town.get(3));
	}

	@Test
	void testGetPath() {
		ArrayList<String> path = map.getPath(town[1],town[4]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
	}

	@Test
	void testPopulateTownGraph() {
		try
		{
			map.populateTownGraph(new File("testing.txt"));
			ArrayList<String> town = map.allTowns();
			ArrayList<String> road = map.allRoads();
			assertEquals("DC", town.get(0));
			assertEquals("Town1", town.get(2));
			assertEquals("GoodToGo", road.get(0));
			assertEquals("RVToTown1", road.get(1));
			assertEquals("RoadAB", road.get(2));
		}
		catch(IOException e)
		{
			fail("No exception was thrown");
		}
		
	}

}
