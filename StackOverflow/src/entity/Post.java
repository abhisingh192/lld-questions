package entity;

public abstract class Post {

    private String id;
    private String text;

    private String authorId;

    private int votes;

    public Post(String id, String text, String authorId) {
        this.id = id;
        this.text = text;
        this.authorId = authorId;
        this.votes = 0;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }
}
