package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_test {

	GraphInterface<Town,Road> map;
	Town[] town;
	@BeforeEach
	void setUp() throws Exception {
		map = new Graph();
		town = new Town[6];
		for(int i = 1; i < 6; i++)
		{
			town[i] = new Town("Town_" + i);
			map.addVertex(town[i]);
		}
		  map.addEdge(town[1], town[2], 2, "Road_12");
		  map.addEdge(town[1], town[5], 10, "Road_15");
		  map.addEdge(town[2], town[5], 6, "Road_25");
		  map.addEdge(town[3], town[2], 3, "Road_23");
		

	}

	@AfterEach
	void tearDown() throws Exception {
		map = null;
	}

	@Test
	void testGetEdge() {
		assertEquals(new Road(town[2], town[5],6, "Road_25"), map.getEdge(town[2], town[5]));
		assertEquals(new Road(town[3], town[2],3, "Road_23"), map.getEdge(town[2], town[3]));
	}

	@Test
	void testAddEdge() {
		assertEquals(false, map.containsEdge(town[3], town[5]));
		map.addEdge(town[3], town[5], 1, "Road_35");
		assertEquals(true, map.containsEdge(town[3], town[5]));
	}

	@Test
	void testAddVertex() {
		Town test = new Town("Town_AB");
		assertEquals(false, map.containsVertex(test));
		map.addVertex(test);
		assertEquals(true, map.containsVertex(test));
	}

	@Test
	void testContainsEdge() {
		assertEquals(true, map.containsEdge(town[2], town[3]));
		assertEquals(true, map.containsEdge(town[1], town[5]));
		assertEquals(false, map.containsEdge(town[1], town[4]));
	}

	@Test
	void testContainsVertex() {
		assertEquals(true, map.containsVertex(new Town("Town_2")));
		assertEquals(false, map.containsVertex(new Town("Town_12")));
	}

	@Test
	void testEdgeSet() {
		Set<Road> roads = map.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_12", roadArrayList.get(0));
		assertEquals("Road_15", roadArrayList.get(1));
		assertEquals("Road_23", roadArrayList.get(2));
		assertEquals("Road_25", roadArrayList.get(3));
	}

	@Test
	void testEdgesOf() {
		Set<Road> roads = map.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_12", roadArrayList.get(0));
		assertEquals("Road_15", roadArrayList.get(1));
	}

	@Test
	void testRemoveEdge() {
		assertEquals(true, map.containsEdge(town[2], town[3]));
		map.removeEdge(town[2], town[3], 3, "Road_23");
		assertEquals(false, map.containsEdge(town[2], town[3]));
	}

	@Test
	void testRemoveVertex() {
		assertEquals(true, map.containsVertex(town[2]));
		map.removeVertex(town[2]);
		assertEquals(false, map.containsVertex(town[2]));
	}

	@Test
	void testVertexSet() {
		Set<Town> roads = map.vertexSet();
		assertEquals(true,roads.contains(town[1]));
		assertEquals(true, roads.contains(town[5]));
	}

	@Test
	void testShortestPath() {
		 String start = "Town_1", end = "Town_5";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = map.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(start))
				  beginIndex = town;
			  if(town.getName().equals(end))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = map.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_1 via Road_12 to Town_2 2 mi",path.get(0).trim());
			  assertEquals("Town_2 via Road_25 to Town_5 6 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");
	}

	
}
