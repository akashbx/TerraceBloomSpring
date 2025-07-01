package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.ProductDto;
import com.example.terracebloom.Entity.Category;
import com.example.terracebloom.Entity.Product;
import com.example.terracebloom.Repository.ProductRepository;
import com.example.terracebloom.Request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        product.setImage(productDto.getImage());
        product.setImage2(productDto.getImage2());
        product.setImage3(productDto.getImage3());
        productRepository.save(product);
    }
    public void updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productRequest.getId()));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCategory(Category.valueOf(productRequest.getCategory()));
        product.setImage(productRequest.getImage());
        product.setImage2(productRequest.getImage2());
        product.setImage3(productRequest.getImage3());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public ProductDto getProductById(Integer id) {
        return ProductDto.from(productRepository.findById(id).get());
    }
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::from).toList();
    }
}
