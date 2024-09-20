package com.musinsa.category.search.common;

import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandRepository;
import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.category.CategoryRepository;
import com.musinsa.category.search.product.ProductDto;
import com.musinsa.category.search.product.ProductRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class SearchService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public SearchService(CategoryRepository categoryRepository, BrandRepository brandRepository,
                         ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    public SearchDto getCategories(String price) {
        List<ProductDto> items = productRepository.findProductsWithUniqueMinAndMaxPrice(new SearchCondition());
        ProductDto.PriceRangeClassifier classify = ProductDto.PriceRangeClassifier.classify(items);

        Supplier<SearchPayload> minSupplier = () -> new SearchPayload.CalculatorBuilder()
                .withItems(classify.getMinPriceItems())
                .build();

        Supplier<SearchPayload> maxSupplier = () -> new SearchPayload.CalculatorBuilder()
                .withItems(classify.getMaxPriceItems())
                .build();

        return new SearchDto(minSupplier, maxSupplier, price);
    }


    public SearchDto getBrandsByName(String name, String price) {
        Brand brand = brandRepository.findByName(name)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.BRAND_NOT_FOUND));

        SearchCondition searchCondition = new SearchCondition(SearchCondition.Type.BRAND, brand.getId());
        List<ProductDto> items = productRepository.findProductsWithUniqueMinAndMaxPrice(searchCondition);
        ProductDto.PriceRangeClassifier classify = ProductDto.PriceRangeClassifier.classify(items);

        Supplier<SearchPayload> minSupplier = () -> new SearchPayload.CalculatorBuilder()
                .withBrand(brand.getName())
                .withItems(classify.getMinPriceItems())
                .build();

        Supplier<SearchPayload> maxSupplier = () -> new SearchPayload.CalculatorBuilder()
                .withBrand(brand.getName())
                .withItems(classify.getMaxPriceItems())
                .build();

        return new SearchDto(minSupplier, maxSupplier, price);
    }


    public SearchDto getCategoriesByName(String name, String price) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new ApiException(ApiExceptionCode.CATEGORY_NOT_FOUND));

        SearchCondition searchCondition = new SearchCondition(SearchCondition.Type.CATEGORY,
                category.getId());

        List<ProductDto> items = productRepository.findProductsWithMinAndMaxPrice(searchCondition);
        ProductDto.PriceRangeClassifier classify = ProductDto.PriceRangeClassifier.classify(items);

        Supplier<SearchPayload> minSupplier = () -> new SearchPayload.Builder()
                .withCategory(category.getName())
                .withItems(classify.getMinPriceItems())
                .build();

        Supplier<SearchPayload> maxSupplier = () -> new SearchPayload.Builder()
                .withCategory(category.getName())
                .withItems(classify.getMaxPriceItems())
                .build();

        return new SearchDto(minSupplier, maxSupplier, price);
    }

    @Getter
    public static class SearchCondition {
        StringBuilder condition = new StringBuilder("WHERE 1=1");
        List<Object> queryParams = new ArrayList<>();

        public SearchCondition() {
            this(Type.NONE, -1L);
        }

        public SearchCondition(Type type, Long id) {
            if (type.equals(Type.BRAND)) {
                condition.append(" AND B.ID = ?1");
                queryParams.add(id);
            } else if (type.equals(Type.CATEGORY)) {
                condition.append(" AND C.ID = ?1");
                queryParams.add(id);
            }
        }

        public enum Type {
            NONE,
            CATEGORY,
            BRAND
        }
    }
}
