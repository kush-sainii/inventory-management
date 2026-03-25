package com.inventory.inventory.management.dto;

import com.inventory.inventory.management.entity.Product;
import java.time.LocalDateTime;

public class InventoryDTO {
    private Long id;
    private Product product;
    private Integer quantity;
    private Integer minQuantity;
    private Integer maxQuantity;
    private LocalDateTime lastUpdated;
    private String warehouseLocation;
    
    public InventoryDTO() {}
    
    public InventoryDTO(Long id, Product product, Integer quantity, Integer minQuantity, Integer maxQuantity, LocalDateTime lastUpdated, String warehouseLocation) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.lastUpdated = lastUpdated;
        this.warehouseLocation = warehouseLocation;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getMinQuantity() {
        return minQuantity;
    }
    
    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }
    
    public Integer getMaxQuantity() {
        return maxQuantity;
    }
    
    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getWarehouseLocation() {
        return warehouseLocation;
    }
    
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }
}
