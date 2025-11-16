import service.CacheService;
import strategy.LRUEvictionPolicy;

public class Main {
    public static void main(String[] args) {

        CacheService<Integer, String> cache =
                new CacheService<>(3, new LRUEvictionPolicy<>());

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCacheState(); // [3=C] [2=B] [1=A]

        cache.get(2);
        cache.printCacheState(); // [2=B] [3=C] [1=A]

        cache.put(4, "D");  // EVICTS 1
        cache.printCacheState(); // [4=D] [2=B] [3=C]

        // Concurrent test
        Thread t1 = new Thread(() -> cache.put(5, "E"));
        Thread t2 = new Thread(() -> System.out.println("Get: " + cache.get(3)));

        t1.start();
        t2.start();
    }
}

