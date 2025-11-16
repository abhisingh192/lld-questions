import entity.*;
import repo.*;
import service.*;
import service.strategy.PaymentMethod;

public class Main {
    public static void main(String[] args) {

        // Repositories
        ProductRepository pr = new ProductRepository();
        CategoryRepository cr = new CategoryRepository();
        InventoryRepository ir = new InventoryRepository();
        UserRepository ur = new UserRepository();
        OrderRepository or = new OrderRepository();

        // Services
        ProductService ps = new ProductService(pr);
        InventoryService inv = new InventoryService(ir);
        CartService cartService = new CartService(pr);
        OrderService orderService = new OrderService(or, pr, inv);
        AuthService auth = new AuthService(ur);

        // Seed data
        Category electronics = new Category("C1", "Electronics");
        cr.add(electronics);

        Product p1 = new Product("P1", "Wireless Headset", "C1", 2999.0, "Noise-cancelling headset");
        Product p2 = new Product("P2", "Phone Case", "C1", 499.0, "Durable protective case");
        pr.add(p1);
        pr.add(p2);

        ir.add(new InventoryItem("P1", 5));
        ir.add(new InventoryItem("P2", 10));

        // Register user and fund wallet
        User u = auth.register("alice", "pass", "alice@example.com");
        u.setWallet(10000.0);
        ur.add(u);

        // User adds products to cart
        cartService.addToCart(u.getId(), "P1", 1);
        cartService.addToCart(u.getId(), "P2", 2);
        Cart cart = cartService.getCart(u.getId());

        // Checkout
        try {
            Order order = orderService.checkout(u.getId(), cart, PaymentMethod.CARD, u);
            System.out.println("Order placed: " + order);
        } catch (Exception ex) {
            System.out.println("Checkout failed: " + ex.getMessage());
        }

        // Show remaining inventory
        System.out.println("Remaining stock P1 = " + inv.getStock("P1"));
        System.out.println("Remaining stock P2 = " + inv.getStock("P2"));
    }
}
