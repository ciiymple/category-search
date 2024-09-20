package com.musinsa.category.search.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.category.search.brand.Brand;
import com.musinsa.category.search.brand.BrandDto;
import com.musinsa.category.search.category.Category;
import com.musinsa.category.search.category.CategoryDto;
import com.musinsa.category.search.common.AbstractBuilder;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private CategoryDto category;
    private BrandDto brand;

    @Nullable
    private Rank rank;

    public ProductDto(Long id, String name, Double price, Long categoryId, String categoryName, Long brandId, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = CategoryDto.create(categoryId, categoryName);
        this.brand = BrandDto.create(brandId, brandName);
    }

    //QueryProjection
    public ProductDto(Long categoryId, String categoryName, Long brandId, String brandName,
                      Long productId, String productName, Double productPrice, Long productMinRank, Long productMaxRank) {
        this.id = productId;
        this.name = productName;
        this.price = productPrice;
        this.category = CategoryDto.create(categoryId, categoryName);
        this.brand = BrandDto.create(brandId, brandName);
        this.rank = Rank.create(productMinRank, productMaxRank);
    }

    public static ProductDto create(Product product, Category category, Brand brand) {
        return new ProductDto(product.getId(),
                product.getName(), product.getPrice(), category.getId(), category.getName(),
                brand.getId(), brand.getName());
    }


    @Data
    public static class Rank {
        private Long min;
        private Long max;

        public Rank(Long min, Long max) {
            this.min = min;
            this.max = max;
        }

        public static Rank create(Long min, Long max) {
            return new Rank(min, max);
        }
    }

    @Getter
    public static class PriceRangeClassifier {
        private List<AbstractBuilder.Payload> minPriceItems;
        private List<AbstractBuilder.Payload> maxPriceItems;

        public PriceRangeClassifier(List<AbstractBuilder.Payload> minPriceItems,
                                    List<AbstractBuilder.Payload> maxPriceItems) {
            this.minPriceItems = minPriceItems;
            this.maxPriceItems = maxPriceItems;
        }

        public static PriceRangeClassifier classify(List<ProductDto> items) {
            List<AbstractBuilder.Payload> minPriceItems = new ArrayList<>();
            List<AbstractBuilder.Payload> maxPriceItems = new ArrayList<>();
            for (ProductDto item : items) {
                AbstractBuilder.Payload payload = AbstractBuilder.Payload.builder()
                        .category(item.getCategory().getName())
                        .brand(item.getBrand().getName())
                        .price(item.getPrice()).build();

                if (item.getRank() != null) {
                    if (item.getRank().getMin() == 1) minPriceItems.add(payload);
                    if (item.getRank().getMax() == 1) maxPriceItems.add(payload);
                }
            }
            return new PriceRangeClassifier(minPriceItems, maxPriceItems);
        }
    }
}
