package Entity;

public class Player {
    private String name;
    private String id;
    private int position;

    public Player(String name, String id) {
        this.name = name;
        this.position = 0; // Players start at position 0
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }
}
