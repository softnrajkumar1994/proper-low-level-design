package com.lrucache;

import java.util.HashMap;

public class LRUCache<K, T> {

    private int capacity = -1;
    private ListNode<K, T> head;
    private ListNode<K, T> tail;
    private HashMap<K, ListNode<K, T>> map = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        map = new HashMap<>();
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void put(K key, T value) {
        if (map.containsKey(key)) {
            throw new RuntimeException("Already  present ");
        }
        if (map.size() >= this.capacity) {
            remove(head.getNext());
        }
        ListNode node = new ListNode(key, value);
        add(node);

    }

    public T get(K key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("No cache");
        }
        ListNode node = map.get(key);

        remove(node);
        add(node);

        return (T) node.getValue();
    }

    public void add(ListNode node) {
        node.setPrev(tail);
        tail.getPrev().setNext(node);
        tail.setPrev(node);
        tail.setNext(null);
        node.setNext(tail);


        map.put((K) node.getKey(), node);
    }

    public void remove(ListNode node) {


        head.setNext(node.getNext());
        head.getNext().setPrev(head);

        map.remove(node.getKey());
    }

    public void printCache() {
        ListNode temp = head.getNext();
        while (temp != null && temp != tail) {
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
        }
    }
}
