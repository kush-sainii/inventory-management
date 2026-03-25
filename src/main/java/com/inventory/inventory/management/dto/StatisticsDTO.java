package com.inventory.inventory.management.dto;

public class StatisticsDTO {
    private Double totalInventoryValue;
    private Integer totalNumberOfProducts;
    private Double averageStockLevel;
    private InventoryDTO mostStockedItem;
    private InventoryDTO leastStockedItem;
    
    public StatisticsDTO() {}
    
    public StatisticsDTO(Double totalInventoryValue, Integer totalNumberOfProducts, Double averageStockLevel, 
                        InventoryDTO mostStockedItem, InventoryDTO leastStockedItem) {
        this.totalInventoryValue = totalInventoryValue;
        this.totalNumberOfProducts = totalNumberOfProducts;
        this.averageStockLevel = averageStockLevel;
        this.mostStockedItem = mostStockedItem;
        this.leastStockedItem = leastStockedItem;
    }
    
    public Double getTotalInventoryValue() {
        return totalInventoryValue;
    }
    
    public void setTotalInventoryValue(Double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }
    
    public Integer getTotalNumberOfProducts() {
        return totalNumberOfProducts;
    }
    
    public void setTotalNumberOfProducts(Integer totalNumberOfProducts) {
        this.totalNumberOfProducts = totalNumberOfProducts;
    }
    
    public Double getAverageStockLevel() {
        return averageStockLevel;
    }
    
    public void setAverageStockLevel(Double averageStockLevel) {
        this.averageStockLevel = averageStockLevel;
    }
    
    public InventoryDTO getMostStockedItem() {
        return mostStockedItem;
    }
    
    public void setMostStockedItem(InventoryDTO mostStockedItem) {
        this.mostStockedItem = mostStockedItem;
    }
    
    public InventoryDTO getLeastStockedItem() {
        return leastStockedItem;
    }
    
    public void setLeastStockedItem(InventoryDTO leastStockedItem) {
        this.leastStockedItem = leastStockedItem;
    }
}
