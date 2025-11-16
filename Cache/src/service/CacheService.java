package service;

import entity.Node;
import strategy.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class CacheService<K,V> {

    private int capacity;
    private EvictionPolicy<K,V> evictionPolicy;
    private Map<K, Node<K,V>> storage;
    private ReentrantLock lock = new ReentrantLock();

    public CacheService(int capacity, EvictionPolicy<K,V> evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = new HashMap<>();
    }

    public V get(K key) {
        lock.lock();
        try {
            if (!storage.containsKey(key)) {
                return null;
            }
            Node<K,V> node = storage.get(key);
            evictionPolicy.keyAccessed(node, true);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (storage.containsKey(key)) {
                Node<K, V> node = storage.get(key);
                node.value = value;
                evictionPolicy.keyAccessed(node, true);
                return;
            }

            if (storage.size() == capacity) {
                Node<K, V> toEvict = evictionPolicy.evictKey();
                storage.remove(toEvict.key);
            }

            Node<K, V> newNode = new Node<>(key, value);
            storage.put(key, newNode);
            evictionPolicy.keyAccessed(newNode, false);

        } finally {
            lock.unlock();
        }
    }

    public void printCacheState() {
        lock.lock();
        try {
            Node<K,V> current = ((strategy.LRUEvictionPolicy<K,V>) evictionPolicy).getHeadNext();
            System.out.print("Cache State: ");
            while (current != null && current.key != null) {
                System.out.print("[" + current.key + ":" + current.value + "] ");
                current = current.next;
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }


}
