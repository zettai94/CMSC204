package application;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class anotherPersonal {

	public static void main(String[] args) {
		TownGraphManager map = new TownGraphManager();
		String[] town = new String[12];
		map = new TownGraphManager();
		town = new String[6];
		for(int i = 1; i < 6; i++)
		{
			town[i] = "Town_" + i;
			map.addTown(town[i]);
		}
		map.addRoad(town[1], town[2], 2, "Road_12");
		map.addRoad(town[1], town[5], 10, "Road_15");
		map.addRoad(town[2], town[5], 6, "Road_25");
		map.addRoad(town[2], town[3], 3, "Road_23");
		map.addRoad(town[3], town[4], 3, "Road_34");
		
		ArrayList<String> roads = map.allRoads();
		System.out.println( roads.get(0));
		System.out.println( roads.get(1));
		System.out.println(roads.get(2));
		System.out.println( roads.get(3));	
		
		
		  //ArrayList<String> path = map.getPath(town[1],town[9]);
		  //System.out.println(path);
//		  System.out.println(graph.containsRoadConnection(town[1], town[11]));
		 // ArrayList<String> roads = map.allTowns();
		 // System.out.println(roads);

		  
	}

}
