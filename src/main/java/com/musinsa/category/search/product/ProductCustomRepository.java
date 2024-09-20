package com.musinsa.category.search.product;

import com.musinsa.category.search.common.SearchService;

import java.util.List;

public interface ProductCustomRepository {
    List<ProductDto> findProductsWithMinAndMaxPrice(SearchService.SearchCondition searchCondition);
    List<ProductDto> findProductsWithUniqueMinAndMaxPrice(SearchService.SearchCondition searchCondition);
}
