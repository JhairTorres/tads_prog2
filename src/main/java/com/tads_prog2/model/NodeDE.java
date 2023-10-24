package com.tads_prog2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

@Data
public class NodeDE {
    @JsonManagedReference
    private Kid data;
    private NodeDE next;
    private NodeDE previous;

    public NodeDE(Kid data) {
        this.data = data;
    }
}
