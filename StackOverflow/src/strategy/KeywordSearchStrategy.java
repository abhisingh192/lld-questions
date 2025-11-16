package strategy;

import entity.Question;
import repo.DataStore;

import java.util.ArrayList;
import java.util.List;

public class KeywordSearchStrategy implements SearchStrategy {

    @Override
    public List<Question> search(DataStore store, String keyword) {
        String k = keyword.toLowerCase();
        List<Question> out = new ArrayList<>();
        for (Question q : store.getQuestions().values()) {
            if (q.getTitle().toLowerCase().contains(k) || q.getText().toLowerCase().contains(k)) {
                out.add(q);
            }
        }
        return out;
    }
}
