package entity;

public class Signal {
    private SignalType type;
    private int duration;

    public Signal(SignalType type, int duration) {
        this.type = type;
        this.duration = duration;
    }


    public SignalType getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public void setType(SignalType type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



}
