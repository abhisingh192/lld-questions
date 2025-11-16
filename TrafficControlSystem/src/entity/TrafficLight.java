package entity;

public class TrafficLight {

    private Road road;
    private Signal signal;

    public TrafficLight(Road road, Signal signal) {
        this.road = road;
        this.signal = signal;
    }

    public Road getRoad() {
        return road;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public void changeSignal(Signal newSignal) {
        this.signal = newSignal;
        System.out.println("Signal changed for " + road.getName() + " → " + newSignal.getType() +
                " (" + newSignal.getDuration() + "s)");
    }
}
