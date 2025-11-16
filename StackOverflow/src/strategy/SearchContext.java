package strategy;

import entity.Question;
import repo.DataStore;

import java.util.List;

public class SearchContext {

    private SearchStrategy strategy;

    public SearchContext(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Question> executeSearch(DataStore store, String query) {
        if (strategy == null) throw new IllegalStateException("Search strategy not set");
        return strategy.search(store, query);
    }
}
