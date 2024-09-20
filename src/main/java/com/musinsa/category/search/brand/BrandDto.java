package com.musinsa.category.search.brand;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.category.search.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDto {
    private Long id;
    private String name;
    private List<Product> products;

    private BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private BrandDto(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public static BrandDto create(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName(), brand.getProducts());
    }

    public static BrandDto create(Long id, String name) {
        return new BrandDto(id, name);
    }
}
