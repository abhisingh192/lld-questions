package entity;

public class Score {
    private int runs;
    private int wickets;
    private int overs;

    public Score() {
        this.runs = 0;
        this.wickets = 0;
        this.overs = 0;
    }

    public synchronized void updateScore(int runs, int wickets, int overs) {
        this.runs += runs;
        this.wickets += wickets;
        this.overs = overs;
    }
    @Override
    public String toString() {
        return runs + "/" + wickets + " in " + overs + " overs";
    }
}
