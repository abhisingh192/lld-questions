package strategy;

import entity.Question;
import repo.DataStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TagSearchStrategy implements SearchStrategy {
    @Override
    public List<Question> search(DataStore store, String tag) {
        Set<String> ids = store.getTagIndex().getOrDefault(tag.toLowerCase(), Collections.emptySet());
        List<Question> out = new ArrayList<>();
        for (String id : ids) {
            Question q = store.getQuestions().get(id);
            if (q != null) out.add(q);
        }
        return out;
    }
}
