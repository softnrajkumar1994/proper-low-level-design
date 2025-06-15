package com.lrucache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListNode<K, T> {
    private ListNode next;
    private ListNode prev;
    private K key;
    private T value;

    public ListNode(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public void setNext(ListNode node) {
        this.next = node;
    }

    public void setPrev(ListNode node) {
        this.prev = node;
    }
}
