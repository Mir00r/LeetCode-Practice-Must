package blind75;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  // Doubly Linked List Node
  private class Node {
    int key, value;
    Node prev, next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }


  private final int capacity;
  private final Map<Integer, Node> cache; // Key-Node map for O(1) lookups
  private final Node head, tail; // Dummy nodes for easy operations

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.cache = new HashMap<>();

    head = new Node(0, 0); // Dummy head
    tail = new Node(0, 0); // Dummy tail
    head.next = tail;
    tail.prev = head;
  }

  // Get the value of a key if it exists in cache, otherwise return -1
  public int get(int key) {
    if (!cache.containsKey(key)) {
      return -1;
    }
    Node node = cache.get(key);
    moveToHead(node); // Move accessed node to the head (most recently used)
    return node.value;
  }

  // Put a key-value pair into the cache, evicting if necessary
  public void put(int key, int value) {
    if (cache.containsKey(key)) {
      Node node = cache.get(key);
      node.value = value;
      moveToHead(node); // Move updated node to the head
    } else {
      Node newNode = new Node(key, value);
      cache.put(key, newNode);
      addToHead(newNode);

      if (cache.size() > capacity) {
        removeLRU(); // Remove least recently used element
      }
    }
  }

  // Move a node to the head (most recently used)
  private void moveToHead(Node node) {
    removeNode(node);
    addToHead(node);
  }

  // Add a node right after the head
  private void addToHead(Node node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  // Remove a node from the list
  private void removeNode(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  // Remove least recently used node (tail's previous node)
  private void removeLRU() {
    Node lru = tail.prev;
    cache.remove(lru.key);
    removeNode(lru);
  }
}
