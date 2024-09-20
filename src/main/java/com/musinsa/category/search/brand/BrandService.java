package com.musinsa.category.search.brand;

import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.category.CategoryRepository;
import com.musinsa.category.search.common.ApiException;
import com.musinsa.category.search.common.ApiExceptionCode;
import com.musinsa.category.search.product.Product;
import com.musinsa.category.search.product.ProductRepository;
import com.musinsa.category.search.product.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public BrandService(BrandRepository brandRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public BrandDto get(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
        return BrandDto.create(brand);
    }

    @Transactional
    public BrandDto create(BrandDto brandDto) {
        brandRepository.findByName(brandDto.getName()).ifPresent(value -> {
            throw new ApiException(ApiExceptionCode.BRAND_NAME_DUPLICATE);
        });

        Brand brand = Brand.create(brandDto);
        return BrandDto.create(brandRepository.save(brand));
    }

    @Transactional
    public BrandDto update(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
        brand.update(brandDto);
        return BrandDto.create(brandRepository.save(brand));
    }

    @Transactional
    public BrandDto delete(Long id) {
        Brand target = brandRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
        brandRepository.delete(target);
        return BrandDto.create(target);
    }

    @Transactional
    public List<ProductDto> create(Long brandId, List<ProductDto> productDto) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductDto p : productDto) {
            Long categoryId = p.getCategory().getId();
            Category category = categoryRepository.findById(categoryId).
                    orElseThrow(() -> new ApiException(ApiExceptionCode.CATEGORY_NOT_FOUND));
            Brand brand = brandRepository.findById(brandId).
                    orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));
            Product product = Product.create(p, category, brand);
            Product createdProduct = productRepository.save(product);
            productDtoList.add(ProductDto.create(createdProduct, category, brand));
        }
        return productDtoList;
    }
}
