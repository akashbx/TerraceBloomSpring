package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String category;
    private String image;
    private String image2;
    private String image3;

    public static ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().name());
        productDto.setImage(product.getImage());
        productDto.setImage2(product.getImage2());
        productDto.setImage3(product.getImage3());
        return productDto;
    }
}
