package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	Town test;
	@BeforeEach
	void setUp() throws Exception {
		String name = "Town A";
		test = new Town(name);
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	void testCompareTo() {
		Town another = new Town("Town B");
		assertEquals(-1, test.compareTo(another));
	}

	@Test
	void testGetName() {
		assertEquals("Town A", test.getName());
	}


	@Test
	void testEqualsObject() {
		Town another = new Town("Town B");
		assertEquals(false, test.equals(another));
	}

	@Test
	void testToString() {
		assertEquals("Town A", test.toString());
	}
	
	@Test
	void testGetSetAdjTown()
	{
		Town t2 = new Town("Town B");
		Town t3 = new Town("Town A");
		Set<Town> expected = new HashSet<>();
		expected.add(t2);
		assertTrue(test.getAdjacentTown().isEmpty());
		//Set<Town> actual = test.getAdjacentTown();
		
		test.addAdjacentTown(t2);
		assertEquals(expected, test.getAdjacentTown());
		test.addAdjacentTown(t3); //wont be added coz it's same town
		assertEquals(expected, test.getAdjacentTown());
		
	}

}
