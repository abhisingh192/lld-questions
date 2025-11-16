package entity;

import java.util.List;

public class Answer extends Post {

    private String questionId;
    private int votes;


    public Answer(String id, String text, String authorId, String questionId) {
        super(id, text, authorId);
        this.questionId = questionId;
        this.votes = 0;

    }

    public int getVotes() {
        return votes;
    }
}
