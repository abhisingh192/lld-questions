package repo;

import entity.Answer;
import entity.Comment;
import entity.Question;
import entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStore {

    Map<String, User> users = new HashMap<>();
    Map<String, Question> questions = new HashMap<>();
    Map<String, Answer> answers = new HashMap<>();
    Map<String, Comment> comments = new HashMap<>();
    Map<String, Set<String>> tagIndex = new HashMap<>();


    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, Question> getQuestions() {
        return questions;
    }

    public Map<String, Answer> getAnswers() {
        return answers;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public Map<String, Set<String>> getTagIndex() {
        return tagIndex;
    }
}
