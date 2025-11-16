package entity;

public class Comment extends Post {

    String parentPostId; // could be question OR answer

    public Comment(String id, String text, String authorId, String parentPostId) {
        super(id, text, authorId);
        this.parentPostId = parentPostId;
    }
}
