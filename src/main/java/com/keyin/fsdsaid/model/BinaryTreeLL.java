package com.keyin.fsdsaid.model;


import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLL {
    BinaryNode root;

    public BinaryTreeLL() {
        this.root = null;
    }

    // Preorder traversal
    public void preorder(BinaryNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getValue() + " ");
        preorder(node.getLeft());
        preorder(node.getRight());
    }

    // InOrder Traversal
    public void inorder(BinaryNode node) {
        if (node == null) {
            return;
        }
        inorder(node.getLeft());
        System.out.println(node.getValue() + " ");
        inorder(node.getRight());
    }

    // Post Order Traversal
    public void postorder(BinaryNode node) {
        if (node == null) {
            return;
        }
        postorder(node.getLeft());
        postorder(node.getRight());
        System.out.println(node.getValue() + " ");
    }

    // Level Order traversal
    public void levelOrder() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode presentNode = queue.remove();
            System.out.println(presentNode.getValue() + " ");
            if (presentNode.getLeft() != null) {
                queue.add(presentNode.getLeft());
            }
            if (presentNode.getRight() != null) {
                queue.add(presentNode.getRight());
            }
        }
    }

    // Search method using level order traversal
    public void search(int value) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode presentNode = queue.remove();
            if (presentNode.getValue() == value) {
                System.out.println("The value-" + value + " is found in the Tree");
                return;
            } else {
                if (presentNode.getLeft() != null) {
                    queue.add(presentNode.getLeft());
                }
                if (presentNode.getRight() != null) {
                    queue.add(presentNode.getRight());
                }
            }
        }
        System.out.println("The value-" + value + " is not found in the Tree");
    }

    // Insert Method
    public void insert(int value) {
        BinaryNode newNode = new BinaryNode(value);
        if (root == null) {
            root = newNode;
            System.out.println("Inserted new node at Root");
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode presentNode = queue.remove();
            if (presentNode.getLeft() == null) {
                presentNode.setLeft(newNode);
                System.out.println("Successfully Inserted");
                break;
            } else if (presentNode.getRight() == null) {
                presentNode.setRight(newNode);
                System.out.println("Successfully Inserted");
                break;
            } else {
                queue.add(presentNode.getLeft());
                queue.add(presentNode.getRight());
            }
        }
    }
}