package com.keyin.fsdsaid.services;

import com.keyin.fsdsaid.model.BinaryNode;
import com.keyin.fsdsaid.model.BinaryNodeEntity;
import com.keyin.fsdsaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinarySearchTreeService {

    @Autowired
    private UserRepository binaryNodeRepository;

    private BinaryNode root;

    public BinaryNode insertNumbers(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return null; // Handle empty input
        }

        String[] numberArray = numbers.split(",");
        root = null; // Reset root for each insertion

        for (String number : numberArray) {
            int value = Integer.parseInt(number.trim());
            root = insertIntoBinarySearchTree(root, value);
        }

        return root;
    }

    private BinaryNode insertIntoBinarySearchTree(BinaryNode root, int value) {
        if (root == null) {
            return new BinaryNode(value);
        }

        if (value < root.getValue()) {
            root.setLeft(insertIntoBinarySearchTree(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertIntoBinarySearchTree(root.getRight(), value));
        }

        return root;
    }

    @Transactional
    public void saveTree(BinaryNode root) {
        if (root != null) {
            saveNode(root);
        }
    }

    private void saveNode(BinaryNode node) {
        if (node != null) {
            BinaryNodeEntity rootNodeEntity = convertToEntity(node);
            binaryNodeRepository.save(rootNodeEntity);

            // Recursively save the left and right subtrees
            saveNode(node.getLeft());
            saveNode(node.getRight());
        }
    }

    public Iterable<BinaryNode> getAllTrees() {
        Iterable<BinaryNodeEntity> treeEntities = binaryNodeRepository.findAll();
        return convertToNodes(treeEntities);
    }

    // Other methods...

    private BinaryNodeEntity convertToEntity(BinaryNode node) {
        if (node == null) {
            return null;
        }

        BinaryNodeEntity nodeEntity = new BinaryNodeEntity();
        nodeEntity.setValue(node.getValue());
        nodeEntity.setLeft(convertToEntity(node.getLeft()));
        nodeEntity.setRight(convertToEntity(node.getRight()));
        return nodeEntity;
    }

    private BinaryNode convertToNode(BinaryNodeEntity entity) {
        if (entity == null) {
            return null;
        }

        BinaryNode node = new BinaryNode(entity.getValue());
        node.setLeft(convertToNode(entity.getLeft()));
        node.setRight(convertToNode(entity.getRight()));
        return node;
    }

    private Iterable<BinaryNode> convertToNodes(Iterable<BinaryNodeEntity> treeEntities) {
        List<BinaryNode> nodes = new ArrayList<>();
        treeEntities.forEach(entity -> nodes.add(convertToNode(entity)));
        return nodes;
    }
}