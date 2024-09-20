package com.musinsa.category.search.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product(String name, double price, Category category, Brand brand) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }


    public static Product create(ProductDto productDto, Category category, Brand brand) {
        if (productDto.getId() != null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Product id가 잘못되었습니다.");
        if (productDto.getName() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Product name 잘못되었습니다.");
        return new Product(productDto.getName(), productDto.getPrice(), category, brand);
    }

    public void update(ProductDto productDto, Category category, Brand brand) {
        if (productDto.getName() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Product name 잘못되었습니다.");
        if (productDto.getBrand().getId() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Product brand 잘못되었습니다.");
        if (productDto.getCategory().getId() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Product category 잘못되었습니다.");

        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.category = category;
        this.brand = brand;
    }
}