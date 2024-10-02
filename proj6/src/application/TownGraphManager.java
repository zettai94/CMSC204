package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TownGraphManager implements TownGraphManagerInterface{

	//hold an object for Graph
	private Graph map;
	
	public TownGraphManager()
	{
		map = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if(town1== null || town1.isEmpty() || town1.isBlank()|| town2== null || town2.isEmpty() ||town2.isBlank()
				|| roadName== null || roadName.isEmpty() || roadName.isBlank()|| weight <1 || town1.equalsIgnoreCase(town2)
				|| town1.matches("\\d+") || town2.matches("\\d+") || roadName.matches("\\d+"))
		{
			return false;
		}
		Town townA = getTown(town1);
		Town townB = getTown(town2);
		//add town if they havent exist
		if(!map.containsVertex(townA))
		{
			addTown(town1);
		}
		
		if(!map.containsVertex(townB))
		{
			addTown(town2);
		}
		
		if(map.containsEdge(townA, townB) || map.containsEdge(townB, townA))
		{
			return false;
		}
		
		for(Road road: map.edgeSet())
		{
			if(road.getName().equalsIgnoreCase(roadName))
			{
				return false;
			}
		}
		
		map.addEdge(townA, townB, weight, roadName);
		
		return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		if(town1 == null || town1.isEmpty() || town1.isBlank()
				||town2 == null || town2.isEmpty() || town2.isBlank() || town1.equalsIgnoreCase(town2)
				|| town1.matches("\\d+")|| town2.matches("\\d+"))
		{
			return null;
		}
		
		Town townA = getTown(town1);
		Town townB = getTown(town2);
		
		if(townA == null || townB== null)
		{
			return null;
		}
		
		for(Road road: map.edgeSet())
		{

			if((road.getSource().equals(townA) && road.getDestination().equals(townB))
					|| (road.getSource().equals(townB) && road.getDestination().equals(townA)))
			{
				return road.getName();
			}
		}
		return null;
	}

	@Override
	public boolean addTown(String v) {
		if(v == null || v.isEmpty() || v.isBlank() || v.matches("\\d+"))
		{
			return false;
		}
		
		Town temp = new Town(v);
		if(!map.containsVertex(temp))
		{
			map.addVertex(temp);
			return true;
		}
		return false;
	}

	@Override
	public Town getTown(String name) {
		//get a town with given name
		//null if doesnt exist
		if(name == null || name.isEmpty() || name.isBlank() || name.matches("\\d+"))
		{
			return null;
		}
		
		if(containsTown(name))
		{
			for(Town town: map.vertexSet())
			{
				if(town.getName().equalsIgnoreCase(name))
				{
					return town;
				}
			}
		}
	
		
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		if(v == null || v.isEmpty() || v.isBlank() || v.matches("\\d+"))
		{
			return false;
		}
		
		Town temp = new Town(v);
		return map.containsVertex(temp);
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		if(town1 == null || town1.isEmpty() || town1.isBlank() || town1.matches("\\d+")
				||town2 == null || town2.isEmpty() || town2.isBlank() || town2.matches("\\d+")|| town1.equalsIgnoreCase(town2))
		{
			return false;
		}
		
		Town townA = getTown(town1);
		Town townB = getTown(town2);
		
		if(townA == null || townB == null)
		{
			return false;
		}
		
		return map.containsEdge(townA, townB);
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roadList = new ArrayList<>();
		for(Road road: map.edgeSet())
		{
			roadList.add(road.getName());
		}
		Collections.sort(roadList);
		return roadList;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if(town1 == null || town1.isEmpty() || town1.isBlank()|| town1.matches("\\d+")
				||town2 == null || town2.isEmpty() || town2.isBlank()|| town2.matches("\\d+")
				|| road == null || road.isEmpty() || road.isBlank() || road.matches("\\d+")|| town1.equalsIgnoreCase(town2))
		{
			return false;
		}
		Town townA = new Town(town1);
		Town townB = new Town(town2);
		if(containsRoadConnection(town1, town2) && townA!=null && townB!=null)
		{
			for(Road roadSet: map.edgeSet())
			{
				if((roadSet.getSource().equals(townA) && roadSet.getDestination().equals(townB))
						|| (roadSet.getSource().equals(townB) && roadSet.getDestination().equals(townA)))
				{
					if(roadSet.getName().equalsIgnoreCase(road))
					{
						map.removeEdge(townA, townB, roadSet.getDistance(), roadSet.getName());
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		if(v == null || v.isEmpty() || v.isBlank()|| v.matches("\\d+"))
		{
			return false;
		}
		Town temp = getTown(v);
		
		if(temp !=null)
		{
			return map.removeVertex(temp);
		}
		return false;
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> townList = new ArrayList<>();
		for(Town town: map.vertexSet())
		{
			if(town!=null)
			{
				townList.add(town.getName());
			}
		}
		Collections.sort(townList);
		return townList;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		if(town1 == null || town1.isEmpty() || town1.isBlank()|| town1.matches("\\d+")
				||town2 == null || town2.isEmpty() || town2.isBlank() || town2.matches("\\d+")|| town1.equalsIgnoreCase(town2))
		{
			return null;
		}
		
		Town townA = getTown(town1);
		Town townB = getTown(town2);
		
		map.dijkstraShortestPath(townA);
		return map.shortestPath(townA, townB);

	}
	
	public void populateTownGraph(File file) throws FileNotFoundException
	{
		try(BufferedReader read = new BufferedReader(new FileReader(file)))
		{
			String line;
			while((line = read.readLine())!=null)
			{
				String[] tokens = line.split(";");
				if(tokens.length ==3)
				{
					String[] pathWeight = tokens[0].split(",");
				
					String roadName = pathWeight[0].trim();
					//check if contains only digit
					String weightStr = pathWeight[1].trim();
					if(!weightStr.matches("\\d+"))
					{
						continue;
					}
					int weight = Integer.parseInt(weightStr);
					String town1 = tokens[1].trim();
					String town2 = tokens[2].trim();
					addTown(town1);
					addTown(town2);
					addRoad(town1, town2, weight, roadName);
					
				}
				
			}
			read.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
