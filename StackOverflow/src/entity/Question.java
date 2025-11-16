package entity;

import java.io.PipedOutputStream;
import java.util.List;

public class Question extends Post  {

    private String title;
    private List<String> tags;

    private int votes;

    public Question(String id, String text, String authorId, String title, List<String> tags) {
        super(id, text, authorId);
        this.title = title;
        this.tags = tags;
        this.votes = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getVotes() {
        return votes;
    }
}
