package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.ProductDto;
import com.example.terracebloom.Entity.Product;
import com.example.terracebloom.Request.ProductRequest;
import com.example.terracebloom.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public void createProduct(ProductRequest productRequest) {
        productServices.createProduct(productRequest);
    }
    @PutMapping("/update")
    public void updateProduct(ProductRequest productRequest) {
        productServices.updateProduct(productRequest);
    }
    @DeleteMapping("/delete")
    public void deleteProduct(Integer id) {
        productServices.deleteProduct(id);
    }
    @GetMapping("/{id}")
    public ProductDto getProductById(Integer id) {
        return productServices.getProductById(id);
    }
}
