package repo;

import entity.Category;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CategoryRepository {

    private final Map<String, Category> map = new ConcurrentHashMap<>();

    public void add(Category category) {
        map.put(category.getId(), category);
    }

    public Category getById(String categoryId) {
        return map.get(categoryId);
    }

}
