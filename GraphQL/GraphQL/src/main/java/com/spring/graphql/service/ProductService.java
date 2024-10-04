package com.spring.graphql.service;

import com.spring.graphql.entity.Product;
import com.spring.graphql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    //update


    public Product updateStock(int id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product found"));
        product.setStock(quantity);
        productRepository.save(product);
        return product;
    }

    public Product recieveNewShipmentQuantity(int id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product found"));
        product.setStock(product.getStock()+quantity);
        productRepository.save(product);
        return product;
    }
}
