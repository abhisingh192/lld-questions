package service;

import entity.Cart;
import repo.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private final Map<String, Cart> cartMap = new HashMap<>();
    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Cart getCart(String userId) {
        return cartMap.computeIfAbsent(userId, Cart::new);
    }

    public void addToCart(String userId, String productId, int quantity) {
        Cart cart = getCart(userId);
        var product = productRepository.getById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        cart.add(product.getId(), quantity);
    }

    public void removeFromCart(String userId, String productId) {
        getCart(userId).remove(productId);
    }
}
