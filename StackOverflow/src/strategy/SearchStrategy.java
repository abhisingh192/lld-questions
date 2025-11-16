package strategy;

import entity.Question;
import repo.DataStore;

import java.util.List;

public interface SearchStrategy {

    List<Question> search(DataStore store, String query);

}
