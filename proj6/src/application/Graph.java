package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road>{

	//contains set of vertices (Town) & edges (Road)
	private Set<Town> vertiTown;
	private Set<Road> edgesRoad;
	private Map<Town, Town> prevPath;
	
	public Graph()
	{
		vertiTown = new HashSet<>();
		edgesRoad = new HashSet<>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex==null || sourceVertex.equals(destinationVertex))
		{
			return null;
		}
		
		Road edgeRoad = null;
		for(Road edge: edgesRoad)
		{
			//check startA with startB or endB and endA with startB or endB
			if((edge.getSource().equals(sourceVertex) && edge.getDestination().equals(destinationVertex))
					|| (edge.getSource().equals(destinationVertex) && edge.getDestination().equals(sourceVertex)))
			{
				//System.out.println(edge);
				edgeRoad = edge;
			}
				
		}
		
		return edgeRoad;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		//throws IllegalArgumentException if source or target vertices are not found in graph
		//NullPointerException if specified vertices is null
		if(sourceVertex == null || destinationVertex == null || sourceVertex.equals(destinationVertex))
		{
			throw new NullPointerException();
		}
		
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
		{
			throw new IllegalArgumentException();
		}
		
		if(weight <1 || description == null || description.isEmpty() || description.isBlank() || description.matches("\\d+"))
		{
			throw new IllegalArgumentException();
		}
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		edgesRoad.add(newRoad);
		
		return null;
	}

	@Override
	public boolean addVertex(Town v) {
		//throw NullPointerException if v is null
		if(v == null)
		{
			throw new NullPointerException();
		}
		
		//check if containsVertex is true
		if(containsVertex(v))
		{
			//if true, return false, do nth
			return false;
		}
		else
		{
			vertiTown.add(v);
			return true;
		}
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		//return true if and only if graph contains a Road going from source to destination
		//remember undirected; check both invert
		//if source or destination is null, return false
		if(sourceVertex == null || destinationVertex == null || sourceVertex.equals(destinationVertex))
		{
			return false;
		}
		for(Road edge : edgesRoad)
		{
			if((edge.getSource().equals(sourceVertex) && edge.getDestination().equals(destinationVertex))
					|| (edge.getSource().equals(destinationVertex) && edge.getDestination().equals(sourceVertex)))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		//return false if specified v is null
		//true if and only if Graph contains vertiTown contains v
		if(v == null)
		{
			return false;
		}
		
		for(Town town: vertiTown)
		{
			if(town.getName().equalsIgnoreCase(v.getName()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		//return a set of Road contained in this graph
		//set is backed by graph, changes to graph are reflected on set
		//result from alteration in iteration are undefined
		
		return new HashSet<>(edgesRoad);
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		//return a set of all edges touching vertex(the adjacentTown)
		//if no edges are touching vertex, return empty set
		//throw IllegalArgumentException if not found in graph
		//throw NullPointerExcetion if vertex is null
		if(vertex == null)
		{
			throw new NullPointerException();
		}
		
		if(!vertiTown.contains(vertex))
		{
			throw new IllegalArgumentException();
		}
		
		//new set of edges from edgesRoad to vertex(town)
		Set<Road> roadToTown = new HashSet<>();
		for(Road edge: edgesRoad)
		{
			//check both source and destination
			if(edge.getSource().equals(vertex) || edge.getDestination().equals(vertex))
			{
				roadToTown.add(edge);
			}
		}
		
		return roadToTown;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		//remove a Road from source to destination, if the road exist
		//if weight bigger than -1 must be checked
		//if description not null, must be checked
		//return edge if removed; else null
		//call for containsVertex to check condition
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex) || weight <=0
				|| description.isBlank()|| description==null || description.isEmpty()  || description.matches("\\d+")|| sourceVertex.equals(destinationVertex))
		{
			return null;
		}
		
		//place holder for removed edge
		Road removedRoad = null;
		for(Road edge: edgesRoad)
		{
			if((edge.getSource().equals(sourceVertex) && edge.getDestination().equals(destinationVertex)) ||
					(edge.getSource().equals(destinationVertex) && edge.getDestination().equals(sourceVertex)) &&
					edge.getDistance()==weight && edge.getName().equalsIgnoreCase(description))
			{
				removedRoad = edge;
				//break to get out of loop
				break;
			}
		}
		
		//remove only if removedRoad is not null
		if(removedRoad!=null)
		{
			edgesRoad.remove(removedRoad);
		}
		return removedRoad;
	}

	@Override
	public boolean removeVertex(Town v) {
		//if v exist in town, call remove edge before finally remove v
		//return true if the graph contains specified vertex
		//else return false
		if(v == null)
		{
			return false;
		}
		boolean found = false;
		if(vertiTown.contains(v))
		{
			//find the set of edge of v
			found = true;
			Set <Road> toRemove = edgesOf(v);
			//System.out.println(toRemove);
			if(toRemove !=null)
			{
				for(Road removing : toRemove)
				{
					removeEdge(removing.getSource(), removing.getDestination(),
							removing.getDistance(), removing.getName());
				}
			}
			
			return vertiTown.remove(v);
		}
		return found;
	}

	@Override
	public Set<Town> vertexSet() {
		//return set of vertices backed by graph
		//changes in graph are reflected in the set
		//if graph is modified in iteration over set in progress
		//result from iteration are undefined
		return new HashSet<>(vertiTown);
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		//calls dijkstraShortestPath with sourceVertex
		//return an array of String describing the path in format:
		// start "via" road "to" destination
	
		//not sure if need to check here
		//i think check condition sourceVertex and destination at TownGraphManager better
		
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<String> shortestInOrder = new ArrayList<>();
		Town desti = destinationVertex;
		while(prevPath.get(desti) != null)
		{
			Town prev = prevPath.get(desti);
			//System.out.println(prev);
			Road road = getEdge(prev, desti);
			if(road == null)
			{
				desti = prev;
				continue;
			}
			String line = prev.getName() + " via " + road.getName() + " to " + 
			desti.getName() + " " + road.getDistance() + " mi";
			shortestInOrder.add(0, line);
			desti= prev;
		}
		
		return shortestInOrder;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		//populate with vertices and edges
		//set weight for town to max first
		Map<Town, Integer> weight = new HashMap<>();
		Set<Town> toVisit = new HashSet<>();
		prevPath = new HashMap<>();
		
		for(Town town: vertiTown)
		{
			weight.put(town, Integer.MAX_VALUE);
			prevPath.put(town, null);
			//System.out.println(town);
			toVisit.add(town);
		}
		//System.out.println(sourceVertex);
		weight.put(sourceVertex, 0);
		while(!toVisit.isEmpty())
		{
			Town current = null;
			int shortestPath = Integer.MAX_VALUE;
			for(Town town: toVisit)
			{
				if(weight!=null && weight.get(town) < shortestPath)
				{
					shortestPath = weight.get(town);
					//System.out.println(shortestPath);
					current = town;
				}
			}
			if(current == null)
			{
				break;
			}
			
			
			
			for(Road road: edgesRoad)
			{
				if(road.getSource().equals(current))
				{
					int total = weight.get(current) + road.getDistance();
					//System.out.println(total);
					if(toVisit.contains(road.getDestination()) && total< weight.get(road.getDestination()))
					{
						weight.put(road.getDestination(), total);
						//System.out.println("in edgesRoad" + prevPath);
						prevPath.put(road.getDestination(), current);
					}
				}
				else if(road.getDestination().equals(current))
				{
					int total = weight.get(current) + road.getDistance();
					if(toVisit.contains(road.getSource()) && total < weight.get(road.getSource()))
					{
						weight.put(road.getSource(), total);
						prevPath.put(road.getSource(), current);
					}
				}
			}
			
			toVisit.remove(current);
		}
	}

}
