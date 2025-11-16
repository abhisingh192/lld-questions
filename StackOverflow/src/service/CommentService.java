package service;

import entity.Comment;
import repo.DataStore;

import java.util.UUID;

public class CommentService {
    private final DataStore store;

    public CommentService(DataStore store) {
        this.store = store;
    }

    public Comment postCommentOnQuestion(String qId, String userId, String text) {
        Comment c = new Comment(UUID.randomUUID().toString(), text, userId, qId);
        store.getComments().put(c.getId(), c);
//        store.questions.get(qId).comments.add(c);
        return c;
    }

    public Comment postCommentOnAnswer(String aId, String userId, String text) {
        Comment c = new Comment(UUID.randomUUID().toString(), text, userId, aId);
        store.getComments().put(c.getId(), c);
//        store.answers.get(aId).comments.add(c);
        return c;
    }
}
