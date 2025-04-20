package application;
public class Edge {
	private Country adjacentCity;
	private double distance;
	private double cost;
	private double time;

	public Edge(Country adjacentCity, double distance, double cost, double time) {
		this.adjacentCity = adjacentCity;
		this.distance = distance;
		this.cost = cost;
		this.time = time;
	}

	public Country getAdjacentCity() {
		return adjacentCity;
	}

	public double getDistance() {
		return distance;
	}	

	public double getCost() {
		return cost;
	}	

	public double getTime() {
		return time;
	}
}	
