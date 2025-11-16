package service;

import entity.Question;
import repo.DataStore;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class QuestionService {

    private final DataStore store;

    public QuestionService(DataStore store) {
        this.store = store;
    }

    public Question postQuestion(String userId, String title, String text, List<String> tags) {
        Question q = new Question(UUID.randomUUID().toString(), title, text, userId, tags);
        store.getQuestions().put(q.getId(), q);
        for (String tag : tags) {
            store.getTagIndex().computeIfAbsent(tag.toLowerCase(), k -> new HashSet<>()).add(q.getId());
        }
        return q;
    }
}
