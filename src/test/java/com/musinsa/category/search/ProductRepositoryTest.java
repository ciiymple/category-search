package com.musinsa.category.search;

import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandRepository;
import com.musinsa.category.search.common.SearchService;
import com.musinsa.category.search.product.ProductDto;
import com.musinsa.category.search.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;



//Test Case
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    //BrandId, Price
    private Map<Long, Double> sampleData;

    @BeforeEach
    public void setUp() {
        this.sampleData = new HashMap<>();
        this.sampleData.put(1L, 10100d);
        this.sampleData.put(2L, 5100d);
        this.sampleData.put(3L, 3000d);
        this.sampleData.put(4L, 9500d);
        this.sampleData.put(5L, 2500d);
        this.sampleData.put(6L, 1500d);
        this.sampleData.put(7L, 2400d);
        this.sampleData.put(8L, 2000d);
    }

    @Test
    @Disabled
    public void 브랜드별_최저가_상품목록() {
        Brand brand = brandRepository.findById(4L).orElseThrow();
        SearchService.SearchCondition searchCondition = new SearchService
                .SearchCondition(SearchService.SearchCondition.Type.BRAND, brand.getId());
        List<ProductDto> items = productRepository.findProductsWithMinAndMaxPrice(searchCondition);

        for (ProductDto item : items) {
            if (item.getRank().getMin() == 1L) {
                if (this.sampleData.containsKey(item.getCategory().getId())) {
                    Assertions.assertEquals(this.sampleData.get(item.getCategory().getId()), item.getPrice());
                }
            }
        }
    }

    @Test
    @Disabled
    public void 브랜드별_최고가_상품목록() {
        Brand brand = brandRepository.findById(4L).orElseThrow();
        SearchService.SearchCondition searchCondition = new SearchService
                .SearchCondition(SearchService.SearchCondition.Type.BRAND, brand.getId());
        List<ProductDto> items = productRepository.findProductsWithMinAndMaxPrice(searchCondition);
        for (ProductDto item : items) {
            if (item.getRank().getMax() == 1L) {
                if (this.sampleData.containsKey(item.getCategory().getId())) {
                    Assertions.assertEquals(this.sampleData.get(item.getCategory().getId()), item.getPrice());
                }
            }
        }
    }
}
