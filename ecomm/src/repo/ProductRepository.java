package repo;

import entity.Product;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository {
    private final Map<String, Product> map = new ConcurrentHashMap<>();


    public void add(Product product) {
        map.put(product.getId(), product);
    }

    public Product getById(String productId) {
        return map.get(productId);
    }

    public Collection<Product> getAll() {
        return map.values();
    }

}
