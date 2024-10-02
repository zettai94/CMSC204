/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 3
 * Due Date: 10th Mar 2024*/
package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SortedDoubleLinkedList_STUDENT_Test {

	SortedDoubleLinkedList<Student> sortedStudent;
	
	StudentComparator comparatorS;
	
	public Student a = new Student("John", "Computer Science", 23);
	public Student b = new Student("Mary", "Business", 19);
	public Student c = new Student("Jane", "Finance", 25);
	public Student d = new Student("Michael", "Civil Engineer", 20);
	
	@BeforeEach
	void setUp() throws Exception {
		comparatorS = new StudentComparator();
		sortedStudent = new SortedDoubleLinkedList<Student>(comparatorS);
	}

	@AfterEach
	void tearDown() throws Exception {
		comparatorS = null;
		sortedStudent = null;
	}

	@Test
	void testIterator() {
		sortedStudent.add(a);
		sortedStudent.add(b);
		sortedStudent.add(c);
		sortedStudent.add(d);
		ListIterator<Student> iterator = sortedStudent.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(c, iterator.next());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		
		//iterator reached the end, no more next
		try
		{
			iterator.next();
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
	void testAddToEnd() {
		try 
		{
			sortedStudent.addToEnd(d);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	void testAddToFront() {
		try
		{
			sortedStudent.addToFront(a);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	void testRemove() {
		sortedStudent.add(a);
		sortedStudent.add(b);
		sortedStudent.add(c);
		//remove first
		assertEquals(c, sortedStudent.getFirst());
		sortedStudent.remove(c, comparatorS);
		assertEquals(a, sortedStudent.getFirst());
		
	}

	@Test
	void testAdd() {
		sortedStudent.add(a);
		sortedStudent.add(b);
		sortedStudent.add(c);
		sortedStudent.add(d);
		assertEquals(c, sortedStudent.getFirst());
		assertEquals(d, sortedStudent.getLast());
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
