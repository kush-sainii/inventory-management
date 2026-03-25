package com.inventory.inventory.management.service;

import com.inventory.inventory.management.dto.ProductDTO;
import com.inventory.inventory.management.entity.Product;
import com.inventory.inventory.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductCode(productDTO.getProductCode());
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setSupplier(productDTO.getSupplier());
        
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setProductName(productDTO.getProductName());
            p.setPrice(productDTO.getPrice());
            p.setDescription(productDTO.getDescription());
            p.setCategory(productDTO.getCategory());
            p.setSupplier(productDTO.getSupplier());
            
            Product updatedProduct = productRepository.save(p);
            return convertToDTO(updatedProduct);
        }
        return null;
    }
    
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO).orElse(null);
    }
    
    public ProductDTO getProductByCode(String code) {
        Optional<Product> product = productRepository.findByProductCode(code);
        return product.map(this::convertToDTO).orElse(null);
    }
    
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ProductDTO> getProductsByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<ProductDTO> searchProducts(String query) {
        String searchQuery = query.toLowerCase();
        return productRepository.findAll().stream()
                .filter(p -> p.getProductName().toLowerCase().contains(searchQuery) || 
                            p.getProductCode().toLowerCase().contains(searchQuery))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getProductCode(),
            product.getProductName(),
            product.getPrice(),
            product.getDescription(),
            product.getCategory(),
            product.getSupplier()
        );
    }
}
