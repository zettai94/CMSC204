package application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class personalTesting {

	public static void main(String[] args) {
		Town a = new Town("SS");
		Town b = new Town("RV");
		Town c = new Town("SS");
		//System.out.println(b.compareTo(b));
		
		GraphInterface<Town,Road> graph;
		Town[] town;
		graph = new Graph();
		town = new Town[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = new Town("Town_" + i);
//			  System.out.println(town[i]);
			  graph.addVertex(town[i]);
		  }
		  
		  graph.addEdge(town[1], town[2], 2, "Road_1");
		  graph.addEdge(town[1], town[3], 4, "Road_2");
		  graph.addEdge(town[1], town[5], 6, "Road_3");
		  graph.addEdge(town[3], town[7], 1, "Road_4");
		  graph.addEdge(town[3], town[8], 2, "Road_5");
		  graph.addEdge(town[4], town[8], 3, "Road_6");
		  graph.addEdge(town[6], town[9], 3, "Road_7");
		  graph.addEdge(town[9], town[10], 4, "Road_8");
		  graph.addEdge(town[8], town[10], 2, "Road_9");
		  graph.addEdge(town[5], town[10], 5, "Road_10");
		  graph.addEdge(town[10], town[11], 3, "Road_11");
		  graph.addEdge(town[2], town[11], 6, "Road_12");
		  
		  for(Town t : graph.vertexSet())
		  {
			 // System.out.println(t.getName());
		  }
		  //System.out.println(graph.containsVertex(new Town("Town_2")));
		  
		  //System.out.println("checking if town are the same:");
		  //System.out.println((new Road(town[2], town[11],6, "Road_12").equals(graph.getEdge(town[2], town[11]))));
		  //System.out.println(graph.getEdge(town[2], town[11]));
		  //check if removeVertex works
		  //System.out.println(graph.removeVertex(town[3]));
		  //System.out.println(town[1] + " still exist? " + graph.containsVertex(town[1]));
		  //System.out.println(graph.edgeSet());
		  //System.out.println(graph.vertexSet());
		  String beginTown = "Town_1", endTown = "Town_10";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  ArrayList<String> path = new ArrayList<>();
		  while(iterator.hasNext())
		  {    	
			  Town current = iterator.next();
			  if(current.getName().equals(beginTown))
				  beginIndex = current;
			  if(current.getName().equals(endTown))
				  endIndex = current;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {
			  path =graph.shortestPath(beginIndex,endIndex); 
			  
		  }
//		  System.out.println(path.get(0).trim());
//		  System.out.println(path.get(1).trim());
//		  System.out.println(path.get(2).trim());
		  
		  TownGraphManager test = new TownGraphManager();
		  
//		  System.out.println(test.addTown("Town 1"));//true
//		  System.out.println(test.addTown("Town 1"));//false
//		  System.out.println(test.addTown(" ")); //false
//		  System.out.println(test.addRoad("Town 1", "Town 3", 3, "Road 12"));//true
//		  System.out.println(test.addRoad("Town 1", "Town 2", 3, "Road 12"));//false
//		  System.out.println(test.addRoad("Town 1", "Town 3", 4, "Road 12")); //should return false
//		  System.out.println(test.addRoad("Town 1", "Town 3", 3, "Road 1")); //should be false
//		  Town temp = test.getTown("town 1");
//		  System.out.println(temp);
//		  System.out.println(test.addRoad("Town 1", "Town 2", 3, "Road AB"));
//		  System.out.println(test.allRoads());
		  //System.out.println(test.getPath("Town 1", "Town 3").size());
		  
		
	}

}
