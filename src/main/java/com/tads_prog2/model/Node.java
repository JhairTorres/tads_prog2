package com.tads_prog2.model;

import lombok.Data;

@Data
public class Node {
    private Kid data;
    private Node next;

    public Node(Kid data) {
        this.data = data;
    }
}
