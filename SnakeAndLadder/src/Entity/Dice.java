package Entity;

public class Dice {
    int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return (int)(Math.random() * sides) + 1;
    }
}
