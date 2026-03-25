package com.inventory.inventory.management.service;

import com.inventory.inventory.management.dto.InventoryDTO;
import com.inventory.inventory.management.dto.StatisticsDTO;
import com.inventory.inventory.management.entity.Inventory;
import com.inventory.inventory.management.entity.Product;
import com.inventory.inventory.management.repository.InventoryRepository;
import com.inventory.inventory.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public InventoryDTO createInventory(Long productId, InventoryDTO inventoryDTO) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Inventory inventory = new Inventory();
            inventory.setProduct(product.get());
            inventory.setQuantity(inventoryDTO.getQuantity());
            inventory.setMinQuantity(inventoryDTO.getMinQuantity());
            inventory.setMaxQuantity(inventoryDTO.getMaxQuantity());
            inventory.setWarehouseLocation(inventoryDTO.getWarehouseLocation());
            
            Inventory savedInventory = inventoryRepository.save(inventory);
            return convertToDTO(savedInventory);
        }
        return null;
    }
    
    public InventoryDTO updateStock(Long productId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            inv.setQuantity(quantity);
            Inventory updatedInventory = inventoryRepository.save(inv);
            return convertToDTO(updatedInventory);
        }
        return null;
    }
    
    public InventoryDTO addStock(Long productId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            inv.setQuantity(inv.getQuantity() + quantity);
            Inventory updatedInventory = inventoryRepository.save(inv);
            return convertToDTO(updatedInventory);
        }
        return null;
    }
    
    public InventoryDTO removeStock(Long productId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            if (inv.getQuantity() >= quantity) {
                inv.setQuantity(inv.getQuantity() - quantity);
                Inventory updatedInventory = inventoryRepository.save(inv);
                return convertToDTO(updatedInventory);
            }
        }
        return null;
    }
    
    public InventoryDTO getInventoryByProductId(Long productId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        return inventory.map(this::convertToDTO).orElse(null);
    }
    
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<InventoryDTO> getLowStockItems() {
        return inventoryRepository.findAll().stream()
                .filter(inv -> inv.getQuantity() <= inv.getMinQuantity())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Boolean isStockAvailable(Long productId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        return inventory.isPresent() && inventory.get().getQuantity() >= quantity;
    }
    
    public StatisticsDTO getStatistics() {
        List<Inventory> allInventory = inventoryRepository.findAll();
        
        if (allInventory.isEmpty()) {
            return new StatisticsDTO(0.0, 0, 0.0, null, null);
        }
        
        // Calculate total inventory value
        Double totalValue = allInventory.stream()
                .mapToDouble(inv -> inv.getQuantity() * inv.getProduct().getPrice())
                .sum();
        
        // Total number of products
        Integer totalProducts = allInventory.size();
        
        // Average stock level
        Double averageStock = allInventory.stream()
                .mapToDouble(Inventory::getQuantity)
                .average()
                .orElse(0.0);
        
        // Most stocked item
        InventoryDTO mostStocked = allInventory.stream()
                .max((a, b) -> a.getQuantity().compareTo(b.getQuantity()))
                .map(this::convertToDTO)
                .orElse(null);
        
        // Least stocked item
        InventoryDTO leastStocked = allInventory.stream()
                .min((a, b) -> a.getQuantity().compareTo(b.getQuantity()))
                .map(this::convertToDTO)
                .orElse(null);
        
        return new StatisticsDTO(totalValue, totalProducts, averageStock, mostStocked, leastStocked);
    }
    
    private InventoryDTO convertToDTO(Inventory inventory) {
        return new InventoryDTO(
            inventory.getId(),
            inventory.getProduct(),
            inventory.getQuantity(),
            inventory.getMinQuantity(),
            inventory.getMaxQuantity(),
            inventory.getLastUpdated(),
            inventory.getWarehouseLocation()
        );
    }
}
