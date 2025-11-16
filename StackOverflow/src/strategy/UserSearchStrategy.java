package strategy;

import entity.Question;
import repo.DataStore;

import java.util.ArrayList;
import java.util.List;

public class UserSearchStrategy implements SearchStrategy {
    public List<Question> search(DataStore store, String userId) {
        List<Question> out = new ArrayList<>();
        for (Question q : store.getQuestions().values()) {
            if (q.getAuthorId().equals(userId)) out.add(q);
        }
        return out;
    }
}
