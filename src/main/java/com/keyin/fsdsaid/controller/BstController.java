package com.keyin.fsdsaid.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.fsdsaid.model.BinaryNode;
import com.keyin.fsdsaid.services.BinarySearchTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BstController {

    private final BinarySearchTreeService binarySearchTreeService;

    @Autowired
    public BstController(BinarySearchTreeService binarySearchTreeService) {
        this.binarySearchTreeService = binarySearchTreeService;
    }

    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enterNumbers";
    }

    @PostMapping("/process-numbers")
    public ResponseEntity<String> processNumbers(@RequestParam String numbers, Model model) {
        try {
            BinaryNode root = binarySearchTreeService.insertNumbers(numbers);
            binarySearchTreeService.saveTree(root);

            String treeJson = convertTreeToJson(root);

            return ResponseEntity.ok(treeJson);
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.addAttribute("error", "Database operation failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database operation failed");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred during processing. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Processing error");
        }
    }

    private String convertTreeToJson(BinaryNode root) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Return an empty JSON object in case of an error
        }
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        Iterable<BinaryNode> previousTrees = binarySearchTreeService.getAllTrees();
        model.addAttribute("previousTrees", previousTrees);
        return "previousTrees";
    }
}
