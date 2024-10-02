package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	Road test;
	Town town1;
	Town town2;
	@BeforeEach
	void setUp() throws Exception {
		
		town1 = new Town("TownA");
		town2 = new Town("TownB");
		test = new Road(town1 , town2, 4, "RoadAB");
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	void testCompareTo() {
		Road road = new Road(new Town("TownC") , new Town ("TownD"), 4, "RoadAB");
		assertEquals(0, test.compareTo(road));
		
	}

	@Test
	void testGetSource() {
		assertEquals(town1, test.getSource());
	}

	@Test
	void testGetDestination() {
		assertEquals(town2, test.getDestination());
	}

	@Test
	void testGetName() {
		assertEquals("RoadAB", test.getName());
	}

	@Test
	void testGetDistance() {
		assertEquals(4, test.getDistance());
	}

	@Test
	void testToString() {
		assertEquals("RoadAB, 4, TownA, TownB", test.toString());
	}

	@Test
	void testEqualsObject() {
		Road another = new Road(town1 , town2, 4, "RoadAB");
		assertTrue(test.equals(another));
	}

	
}
