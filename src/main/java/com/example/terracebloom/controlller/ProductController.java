package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.ProductDto;
import com.example.terracebloom.Entity.Product;
import com.example.terracebloom.Request.ProductRequest;
import com.example.terracebloom.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
        productServices.createProduct(productDto);
    }
    @PutMapping("/update")
    public void updateProduct(@RequestBody ProductRequest productRequest) {
        productServices.updateProduct(productRequest);
    }
    @DeleteMapping("/delete")
    public void deleteProduct(Integer id) {
        productServices.deleteProduct(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        ProductDto productDto = productServices.getProductById(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDto = productServices.getAllProducts();
        return ResponseEntity.ok(productDto);
    }
}
