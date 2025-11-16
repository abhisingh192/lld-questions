package strategy;

import entity.Node;

public interface EvictionPolicy<K,V>{

    void keyAccessed(Node<K,V> node, boolean isPresent);

    Node<K,V> evictKey();
}
