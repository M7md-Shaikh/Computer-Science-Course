package application;

import javafx.scene.control.Button;

public class Country implements Comparable<Country> {
    float x;
    float y;
    String name;
    int index;  // field for indexing purposes
    LinList<Edge> edges = new LinList<>();
    private double max = Double.MAX_VALUE;
    private Country tempPrev;
    Button test = new Button();

    public Country(String name, float x, float y) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Country() {}

    public void addAdjacentCity(Country country, double distance, double cost, int time) {
        edges.add(new Edge(country, distance, cost, time));
    }

    public void resetTemps() {
        max = Double.MAX_VALUE;
        tempPrev = null;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public LinList<Edge> getAdjacentsList() {
        return edges;
    }

    public void setTempCost(double tempCost) {
        this.max = tempCost;
    }

    public double getTempCost() {
        return max;
    }

    public void setTempPrev(Country tempPrev) {
        this.tempPrev = tempPrev;
    }

    public Country getTempPrev() {
        return tempPrev;
    }

    public String getFullName() {
        return name;
    }

    public Button getTest() {
        return test;
    }

    public void setTest(Button test) {
        this.test = test;
    }

    @Override
    public int compareTo(Country o) {
        return Double.compare(this.max, o.max);
    }

    @Override
    public String toString() {
        return "Country [x=" + x + ", y=" + y + ", name=" + name + "]";
    }
}
