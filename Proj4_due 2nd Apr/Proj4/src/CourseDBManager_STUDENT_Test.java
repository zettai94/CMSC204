/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 4
 * Due Date: 31st Mar 2024*/

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	CourseDBManager test;
	
	@BeforeEach
	void setUp() throws Exception {
		test = new CourseDBManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	void testAdd() {
		try
		{
			test.add("ENG101", 12345, 3, "HM111", "JK Rowling");
			//shouldnt add, the crn is not 5 digit
			test.add("ENG101", 222, 3, "HM111", "JK Rowling");
			assertEquals(null, test.get(222));
		} 
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			fail("Should not throw an exception");
		}
		
	}

	@Test
	void testGet() {
		test.add("ENG101", 12345, 3, "HM111", "JK Rowling");
		test.add("CMSC203", 30333, 4, "SC205", "Potato Sim");
		assertEquals("CMSC203", test.get(30333).getID());
		assertEquals("JK Rowling", test.get(12345).getProfName());
	}

	@Test
	void testReadFile() {
		try
		{
			File input = new File("readFileTest.txt");
			PrintWriter write = new PrintWriter(input);
			write.println("CMSC204 30333 4 SC205 Potato Sim");
			write.println("ENG101 20222 3 HM111 Janet Wilson");
			write.close();
			
			test.readFile(input);
			assertEquals("CMSC204", test.get(30333).getID());
			assertEquals("ENG101", test.get(20222).getID());
			System.out.println(test.get(20222).getProfName());
			assertEquals("Janet Wilson", test.get(20222).getProfName());
		}
		catch(Exception e)
		{
			fail("Should not throw any exception");
		}
	}

	@Test
	void testShowAll() {
		test.add("ENG101", 12345, 3, "HM111", "JK Rowling");
		test.add("CMSC203", 31369, 4, "SC205", "Potato Sim");
		test.add("CMSC204", 31370, 4, "Distance-Learning", "Gary Thai");
		ArrayList<String> course = test.showAll();
		
		assertEquals(course.get(0), "\nCourse:CMSC203 CRN:31369 Credits:4 Instructor:Potato Sim Room:SC205");
		assertEquals(course.get(1), "\nCourse:CMSC204 CRN:31370 Credits:4 Instructor:Gary Thai Room:Distance-Learning");
		assertEquals(course.get(2), "\nCourse:ENG101 CRN:12345 Credits:3 Instructor:JK Rowling Room:HM111");
	}

}
