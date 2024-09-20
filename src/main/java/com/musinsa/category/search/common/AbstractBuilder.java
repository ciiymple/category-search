package com.musinsa.category.search.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

public abstract class AbstractBuilder<T> {

    public abstract T build();
    
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Payload {
        private String category;
        private String brand;
        private double price;

        @Builder
        public Payload(String category, String brand, double price) {
            this.category = category;
            this.brand = brand;
            this.price = price;
        }
    }
}
