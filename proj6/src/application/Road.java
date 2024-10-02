package application;

public class Road implements Comparable<Road>{

	//2 vertices, distance between vertices, name
	private Town start, destination;
	private int distance;
	private String roadName;
	
	public Road(Town a, Town b, int distance, String roadName)
	{
		if(!a.equals(b))
		{
			this.start = a;
			this.destination = b;
			this.distance = distance;
			this.roadName = roadName;
			
			//might be redundant
			start.addAdjacentTown(b);
			destination.addAdjacentTown(a);
		}
		
	}
	
	@Override
	public int compareTo(Road roadObj) {
		//comparing to the name
		return this.roadName.compareTo(roadObj.getName());
	}
	
	//getter
	public Town getSource()
	{
		return this.start;
	}
	
	public Town getDestination()
	{
		return this.destination;
	}
	
	public String getName()
	{
		return this.roadName;
	}
	
	public int getDistance()
	{
		return this.distance;
	}
	
	@Override
	public String toString()
	{
		return this.roadName + ", " + this.distance + ", "
				+ this.start + ", " + this.destination;
		//not sure if only want the name of road
	}
	
	@Override
	public boolean equals(Object another)
	{
		Road temp = (Road) another;
		if(start.equals(temp.getSource()) && destination.equals(temp.getDestination()) ||
				(start.equals(temp.getDestination()) && destination.equals(temp.getSource())) &&
				distance==temp.getDistance() && roadName.equals(temp.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//setter
	public void setSource(Town a)
	{
		this.start = a;
	}
	
	public void setDestination(Town b)
	{
		this.destination = b;
	}
	
	public void setDistance(int distance)
	{
		if(!(distance<1))
		{
			this.distance = distance;
		}
		
	}
	
	public void setRoadName(String name)
	{
		this.roadName = name;
	}
}
