package com.musinsa.category.search;

import com.musinsa.category.search.common.AbstractBuilder;
import com.musinsa.category.search.common.SearchDto;
import com.musinsa.category.search.common.SearchPayload;
import com.musinsa.category.search.common.SearchService;
import com.musinsa.category.search.product.ProductDto;
import com.musinsa.category.search.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SearchServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    public void 카테고리별_상품_최소_최고_총합() {
        List<ProductDto> mockItems = List.of(
                new ProductDto(1L, "상의", 1L, "나이키", 1L, "나이키-1", 1000d, 1L, 2L),
                new ProductDto(1L, "상의", 1L, "나이키", 2L, "나이키-2", 2000d, 2L, 1L),
                new ProductDto(2L, "아우터", 1L, "나이키", 3L, "나이키-3", 1000d, 1L, 2L),
                new ProductDto(2L, "아우터", 1L, "나이키", 4L, "나이키-4", 2000d, 2L, 1L),
                new ProductDto(3L, "바지", 1L, "나이키", 5L, "나이키-5", 2000d, 2L, 1L),
                new ProductDto(3L, "바지", 1L, "나이키", 6L, "나이키-6", 1000d, 1L, 2L),
                new ProductDto(4L, "스니커즈", 1L, "나이키", 7L, "나이키-7", 1000d, 1L, 2L),
                new ProductDto(4L, "스니커즈", 1L, "나이키", 8L, "나이키-8", 1500d, 2L, 1L),
                new ProductDto(5L, "가방", 1L, "나이키", 9L, "나이키-9", 1000d, 1L, 2L),
                new ProductDto(5L, "가방", 1L, "나이키", 10L, "나이키-10", 3000d, 2L, 1L)
        );

        when(productRepository.findProductsWithMinAndMaxPrice(any(SearchService.SearchCondition.class)))
                .thenReturn(mockItems);

        // When
        SearchDto result = searchService.getCategories("all");

        SearchPayload minPayload = result.getMin();
        SearchPayload maxPayload = result.getMax();

        assertNotNull(minPayload);
        assertNotNull(maxPayload);
        double minTotal = 5000;
        double maxTotal = 10500;

        assertEquals(minTotal, minPayload.getItems().stream()
                .map(AbstractBuilder.Payload::getPrice).reduce(0d, Double::sum));
        assertEquals(maxTotal, maxPayload.getItems().stream()
                .map(AbstractBuilder.Payload::getPrice).reduce(0d, Double::sum));
    }
}
