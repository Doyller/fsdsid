package com.keyin.fsdsaid.model;

import javax.persistence.*;

@Entity
public class BinaryNodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "left_child_id")
    private BinaryNodeEntity left;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "right_child_id")
    private BinaryNodeEntity right;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNodeEntity getLeft() {
        return left;
    }

    public void setLeft(BinaryNodeEntity left) {
        this.left = left;
    }

    public BinaryNodeEntity getRight() {
        return right;
    }

    public void setRight(BinaryNodeEntity right) {
        this.right = right;
    }
}
