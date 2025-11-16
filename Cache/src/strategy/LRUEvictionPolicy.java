package strategy;

import entity.DoublyLinkedList;
import entity.Node;

/**
 * Implements LRU eviction using a doubly linked list.
 * Most recently used = head next
 * Least recently used = tail prev
 */
public class LRUEvictionPolicy<K,V> implements EvictionPolicy<K,V> {

    private final DoublyLinkedList<K, V> dll;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
    }
    @Override
    public void keyAccessed(Node<K, V> node, boolean isPresent) {
        if (isPresent)
            dll.removeNode(node);
        dll.addToHead(node);

    }

    @Override
    public Node<K, V> evictKey() {
        return dll.removeFromTail();
    }

    public void addToHead(Node<K, V> node) {
        dll.addToHead(node);
    }

    public Node<K, V> getHeadNext() {
        return dll.getHeadNext();
    }
}
