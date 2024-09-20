package com.musinsa.category.search.brand;

import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import com.musinsa.category.search.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Brand(String name) {
        this.name = name;
    }

    //Test
    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Brand create(BrandDto brandDto) {
        if (brandDto.getId() != null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Brand id가 잘못되었습니다.");
        if (brandDto.getName() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Brand name이 잘못되었습니다.");
        return new Brand(brandDto.getName());
    }

    public void update(BrandDto brandDto) {
        if (brandDto.getName() == null)
            throw new ApiException(ApiExceptionCode.INVALID_DATA, "Brand name이 잘못되었습니다.");
        this.name = brandDto.getName();
    }
}
