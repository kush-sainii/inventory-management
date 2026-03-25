package com.inventory.inventory.management.controller;

import com.inventory.inventory.management.dto.InventoryDTO;
import com.inventory.inventory.management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @PostMapping("/product/{productId}")
    public ResponseEntity<InventoryDTO> createInventory(@PathVariable Long productId, @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO createdInventory = inventoryService.createInventory(productId, inventoryDTO);
        if (createdInventory != null) {
            return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/product/{productId}")
    public ResponseEntity<InventoryDTO> updateStock(@PathVariable Long productId, @RequestBody Map<String, Integer> request) {
        Integer quantity = request.get("quantity");
        InventoryDTO updatedInventory = inventoryService.updateStock(productId, quantity);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/product/{productId}/add")
    public ResponseEntity<InventoryDTO> addStock(@PathVariable Long productId, @RequestBody Map<String, Integer> request) {
        Integer quantity = request.get("quantity");
        InventoryDTO updatedInventory = inventoryService.addStock(productId, quantity);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/product/{productId}/remove")
    public ResponseEntity<InventoryDTO> removeStock(@PathVariable Long productId, @RequestBody Map<String, Integer> request) {
        Integer quantity = request.get("quantity");
        InventoryDTO updatedInventory = inventoryService.removeStock(productId, quantity);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryDTO> getInventoryByProductId(@PathVariable Long productId) {
        InventoryDTO inventory = inventoryService.getInventoryByProductId(productId);
        if (inventory != null) {
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        List<InventoryDTO> inventory = inventoryService.getAllInventory();
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }
    
    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryDTO>> getLowStockItems() {
        List<InventoryDTO> lowStockItems = inventoryService.getLowStockItems();
        return new ResponseEntity<>(lowStockItems, HttpStatus.OK);
    }
    
    @GetMapping("/available/{productId}/{quantity}")
    public ResponseEntity<Map<String, Boolean>> checkStockAvailability(@PathVariable Long productId, @PathVariable Integer quantity) {
        Boolean isAvailable = inventoryService.isStockAvailable(productId, quantity);
        return new ResponseEntity<>(Map.of("available", isAvailable), HttpStatus.OK);
    }
}
