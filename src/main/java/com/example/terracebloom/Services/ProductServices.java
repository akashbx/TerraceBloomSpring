package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.ProductDto;
import com.example.terracebloom.Entity.Product;
import com.example.terracebloom.Repository.ProductRepository;
import com.example.terracebloom.Request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImage(productRequest.getImage());
        productRepository.save(product);
    }
    public void updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productRequest.getId()));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImage(productRequest.getImage());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDto getProductById(Integer id) {
        return ProductDto.from(productRepository.findById(id).get());
    }
}
