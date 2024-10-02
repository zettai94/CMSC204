/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 3
 * Due Date: 10th Mar 2024*/
package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<Student> linkedS;
	
	StudentComparator comparatorS;
	
	public Student a = new Student("John", "Computer Science", 23);
	public Student b = new Student("Mary", "Business", 19);
	public Student c = new Student("Jane", "Finance", 25);
	public Student d = new Student("Michael", "Civil Engineer", 20);
	
	public ArrayList<Student> students = new ArrayList<Student>();
	
	
	@BeforeEach
	void setUp() throws Exception {
		linkedS = new BasicDoubleLinkedList<Student>();
		linkedS.addToEnd(a);
		linkedS.addToEnd(b);
		comparatorS = new StudentComparator();
	}

	@AfterEach
	void tearDown() throws Exception {
		linkedS = null;
		comparatorS = null;
	}

	@Test
	void testIterator() {
		linkedS.addToFront(d);
		linkedS.addToEnd(c);
		ListIterator<Student> iteratorS = linkedS.iterator();
		assertEquals(true, iteratorS.hasNext());
		assertEquals(false, iteratorS.hasPrevious());
		assertEquals(d, iteratorS.next());
		assertEquals(a, iteratorS.next());
		assertEquals(b, iteratorS.next());
		assertEquals(c, iteratorS.next());
		assertEquals(false, iteratorS.hasNext());
		assertEquals(true, iteratorS.hasPrevious());
		
		try
		{
			iteratorS.next();
			assertTrue("did not throw a NoSuchElementException", false);
			
		}
		catch(NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}

	@Test
	void testGetSize() {
		assertEquals(2, linkedS.getSize());
		linkedS.addToEnd(c);
		assertEquals(3, linkedS.getSize());
	}

	@Test
	void testAddToEnd() {
		assertEquals(b, linkedS.getLast());
		linkedS.addToEnd(c);
		assertEquals(c, linkedS.getLast());
	}

	@Test
	void testAddToFront() {
		assertEquals(a, linkedS.getFirst());
		linkedS.addToFront(d);
		assertEquals(d, linkedS.getFirst());
	}

	@Test
	void testGetFirst() {
		assertEquals(a, linkedS.getFirst());
		linkedS.addToFront(c);
		assertEquals(c, linkedS.getFirst());
	}

	@Test
	void testGetLast() {
		assertEquals(b, linkedS.getLast());
		linkedS.addToEnd(d);
		assertEquals(d, linkedS.getLast());
		linkedS.addToEnd(c);
		assertEquals(c, linkedS.getLast());
	}

	@Test
	void testRemove() {
		assertEquals(a, linkedS.getFirst());
		assertEquals(b, linkedS.getLast());
		linkedS.remove(a, comparatorS);
		assertEquals(b, linkedS.getFirst());
		linkedS.addToEnd(c);
		assertEquals(c, linkedS.getLast());
		
	}

	@Test
	void testRetrieveFirstElement() {
		assertEquals(a, linkedS.retrieveFirstElement());
		//add d to first element
		linkedS.addToFront(d);
		assertEquals(d, linkedS.retrieveFirstElement());
		//add c to first element
		linkedS.addToFront(c);
		assertEquals(c, linkedS.retrieveFirstElement());
	}

	@Test
	void testRetrieveLastElement() {
		assertEquals(b, linkedS.getLast());
		linkedS.addToEnd(d);
		assertEquals(d, linkedS.getLast());
		linkedS.addToEnd(c);
		assertEquals(c, linkedS.getLast());
	}

	@Test
	void testToArrayList() {
		linkedS.addToFront(c);
		linkedS.addToEnd(d);
		students = linkedS.toArrayList();
		assertEquals(c, students.get(0));
		assertEquals(a, students.get(1));
		assertEquals(b, students.get(2));
		assertEquals(d, students.get(3));
	}
	
	private class StudentComparator implements Comparator<Student>
	{
		@Override
		public int compare(Student s1, Student s2) {
			return s1.toString().compareTo(s2.toString());
		}
	}
	
	private class Student
	{
		String name;
		String course;
		int age;
		
		public Student(String name, String course, int age)
		{
			this.name = name;
			this.course = course;
			this.age = age;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public String getCourse()
		{
			return this.course;
		}
		
		public int getAge()
		{
			return this.age;
		}
		
		@Override
		public String toString()
		{
			return (getName() + " " + getCourse() + " " + getAge());
		}
	}

}
