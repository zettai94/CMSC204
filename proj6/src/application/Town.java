/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 6
 * Due Date: 5th May 2024*/
package application;

import java.util.HashSet;
import java.util.Set;

public class Town implements Comparable<Town>{

	//name of town and adjacent towns; other field if desired
	private String townName;
	//make sure no duplicate adjTown
	private Set<Town> adjTowns;
	
	//constructor
	public Town(String name)
	{
		this.townName = name;
		this.adjTowns = new HashSet<>();
	}
	
	@Override
	public int compareTo(Town another)
	{ 
		//for alphabetical order; later use
		return this.townName.compareTo(another.getName());
	}
	
	//getter
	public String getName()
	{
		return this.townName;
	}
	
	public Set<Town> getAdjacentTown()
	{
		return new HashSet<>(adjTowns);
	}
	
	@Override
	public boolean equals(Object something)
	{
		return((Town) something).getName().equalsIgnoreCase(this.townName);
	}
	
	@Override
	public String toString()
	{
		return this.townName;
	}

	//setter
	public void setTownName(String name)
	{
		this.townName = name;
	}
	
	public void addAdjacentTown(Town name)
	{
		if(!equals(name))
		{
			adjTowns.add(name);
		}
		
	}
	
}
