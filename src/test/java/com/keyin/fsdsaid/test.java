package com.keyin.fsdsaid;

import com.keyin.fsdsaid.controller.BstController;
import com.keyin.fsdsaid.services.BinarySearchTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BstControllerTest {

    private final BinarySearchTreeService binarySearchTreeService = new BinarySearchTreeService();
    private final BstController bstController = new BstController(binarySearchTreeService);

    @Test
    void testProcessNumbers_Success() {
        String validNumbers = "10 20 30";

        ResponseEntity<String> result = bstController.processNumbers(validNumbers, null);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("jsonView", result.getBody());
    }

    @Test
    void testProcessNumbers_Error() {
        String invalidNumbers = "abc";

        ResponseEntity<String> result = bstController.processNumbers(invalidNumbers, null);

        assertEquals(500, result.getStatusCodeValue());
        assertEquals("error", result.getBody());
    }

    @Test
    void testPreviousTrees() {
        String result = bstController.previousTrees(null);

        assertEquals("previousTrees", result);
    }
}
