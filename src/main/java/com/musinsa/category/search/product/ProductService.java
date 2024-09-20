package com.musinsa.category.search.product;

import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandRepository;
import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.category.CategoryRepository;
import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategory().getId()).
                orElseThrow(() -> new ApiException(ApiExceptionCode.CATEGORY_NOT_FOUND));
        Brand brand = brandRepository.findById(productDto.getBrand().getId()).
                orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
        Product target = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.PRODUCT_NOT_FOUND));

        target.update(productDto, category, brand);
        Product updated = productRepository.save(target);
        return ProductDto.create(updated, category, brand);
    }

    @Transactional
    public ProductDto delete(Long id) {
        Product target = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.PRODUCT_NOT_FOUND));
        Category category = categoryRepository.findById(target.getCategory().getId()).
                orElseThrow(() -> new ApiException(ApiExceptionCode.CATEGORY_NOT_FOUND));
        Brand brand = brandRepository.findById(target.getBrand().getId()).
                orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
        productRepository.delete(target);
        return ProductDto.create(target, category, brand);
    }
}


