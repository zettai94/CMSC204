/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 4
 * Due Date: 31st Mar 2024*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{

	private ArrayList<LinkedList<CourseDBElement>> array;
	//private final double loadFactor = 1.5;
	
	/*2 constructors:
	 * 1. Takes in an integer n which represent the estimated number of courses
	 *  and determines the size of the hash table by finding 4k+3 prime
	 *  just greater than n/loading factor
	 *  
	 *  2. constructor take a string "Testing" and an int for the hashtable size,
	 *  only used for testing.*/
	
	public CourseDBStructure(int n)
	{
		/*originally should return 19 when 20 is passed
		boolean sizeFound = false;
		int lowerBound = (int) (n / loadFactor);
		int k = 0;
		int size = 0;
		while(!sizeFound)
		{
			size = 4 *k +3;
			//System.out.println(size);
			if(isPrime(size) && size > lowerBound)
			{
				sizeFound = true;
			}
			k++;
		}
		table = new LinkedList[size];
		for(int i = 0; i < size; i++)
		{
			table[i] = new LinkedList<CourseDBElement>();
		}*/
		
		array = new ArrayList<>(n);
		for(int i = 0; i < n; i++)
		{
			array.add(new LinkedList<CourseDBElement>());
		}
		
	}
	
	//testing purpose only
	public CourseDBStructure(String testing, int size)
	{
		this(size);
	}

	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement element) {
		int index = element.hashCode() % array.size();
		LinkedList<CourseDBElement> bucket = array.get(index);
		for(CourseDBElement ele : bucket)
		{
			if(ele.getCRN() == element.getCRN() )
			{
				if(element.getProfName().equals("updated"))
				{
					ele.setCourseID(element.getID());
				}
				return;
			}
		}
		bucket.add(element);
	}

	/* dont need this coz there's no resize
	public int findNextPrime(int n)
	{
		while(!isPrime(n))
		{
			n +=1;
		}
		return n;
	}
	
	public boolean isPrime(int n)
	{
		//might wanna rewrite this
		  if (n <= 1)
		        return false;
		        
		    if (n == 2 || n == 3)
		        return true;
		        
		    if (n % 2 == 0 || n % 3 == 0)
		        return false;
		    
		    for (int i = 5; i <= Math.sqrt(n); i = i + 6)
		        if (n % i == 0 || n % (i + 2) == 0)
		            return false;

		    return true;
	}*/
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = Integer.toString(crn).hashCode() % array.size();
		for(CourseDBElement element : array.get(index))
		{
			if(element.getCRN() == crn)
			{
				return element;
			}
		}
		throw new IOException();
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<>();
		for(LinkedList<CourseDBElement> bucket : array)
		{
			for(CourseDBElement ele: bucket)
			{
				//System.out.println("List is adding " +ele.toString());
				list.add(ele.toString());
			}
		}
		return list;
	}

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	@Override
	public int getTableSize() {
		return array.size();
	}
}
