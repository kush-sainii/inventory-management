package com.inventory.inventory.management.dto;

public class ProductDTO {
    private Long id;
    private String productCode;
    private String productName;
    private Double price;
    private String description;
    private String category;
    private String supplier;
    
    public ProductDTO() {}
    
    public ProductDTO(Long id, String productCode, String productName, Double price, String description, String category, String supplier) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.supplier = supplier;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getSupplier() {
        return supplier;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
