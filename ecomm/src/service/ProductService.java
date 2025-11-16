package service;

import entity.Product;
import repo.ProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> search(String query) {

        if (query == null || query.isBlank())
            return new ArrayList<>(List.copyOf(productRepository.getAll()));

        return productRepository.getAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()) ||
                                   product.getDescription().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Collection<Product> listAll() {
        return productRepository.getAll();
    }
}
