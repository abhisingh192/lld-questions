package entity;

public class Road {

    private String name;

    private int trafficDensity; // vehicles per minute

    public Road(String name, int trafficDensity) {
        this.name = name;
        this.trafficDensity = trafficDensity;
    }

    public String getName() {
        return name;
    }

    public int getTrafficDensity() {
        return trafficDensity;
    }

    public void setTrafficDensity(int trafficDensity) {
        this.trafficDensity = trafficDensity;
    }
}
