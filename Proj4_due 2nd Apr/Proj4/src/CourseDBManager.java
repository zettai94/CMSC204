/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 4
 * Due Date: 31st Mar 2024*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class CourseDBManager implements CourseDBManagerInterface{

	private CourseDBStructure struc;
	private final int DEFAULT_SIZE = 200;
	
	public CourseDBManager()
	{
		struc = new CourseDBStructure(DEFAULT_SIZE);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		//add CourseDBElement with given info to CourseDBStructure
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		struc.add(element);
	}

	@Override
	public CourseDBElement get(int crn) {
		//finds CouseDBElement based on the crn
		try
		{
			return struc.get(crn);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		
		Scanner read = new Scanner(input);
		while(read.hasNextLine())
		{
			String line = read.nextLine();
			//ignore if line starts with hash or is empty
			if (line.startsWith("#") || line.isEmpty())
			{
		       continue;
			}
		    String[] tokens = line.split(" ");
		    //if less than 4 tokens
		    if (tokens.length < 4)
		    {
		    	continue;
		    }

		    String id = tokens[0];
		    String crn = tokens[1];
		    if (!crn.matches("\\d{5}") || crn.matches(".*[a-zA-Z].*"))
		    {
		        continue; // Skip line if CRN is not 5 digits or contains alphabet characters
		    }

		    int credits;
		    try
		    {
		        credits = Integer.parseInt(tokens[2]);
		        if (credits < 1 || credits > 4)
		        {
		            throw new NumberFormatException();
		        }
		    }
		    catch (NumberFormatException e)
		    {
		        continue; // Skip line if credits are not between 1 and 4
		    }

		    String room = tokens[3];
		    String profName = "";

		    for (int i = 4; i < tokens.length; i++)
		    {
		         profName += tokens[i] + " ";
		    }
		    /*System.out.println("adding these: " +id + " " + Integer.parseInt(crn) + 
		    		" "+ credits+ " " +room + " " + profName);*/
			add(id, Integer.parseInt(crn), credits, room, profName.trim());
		}	
		read.close();
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = struc.showAll();
		ArrayList<String> amend = new ArrayList<>();
		for(String ele: list)
		{
			amend.add("\n" + ele);
		}
		return amend;
		
	}
	
	
}
