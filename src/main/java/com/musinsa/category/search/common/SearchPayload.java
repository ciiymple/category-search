package com.musinsa.category.search.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchPayload {

    @Nullable
    private String category;

    @Nullable
    private String brand;

    private List<AbstractBuilder.Payload> items;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double total = 0;

    private SearchPayload(CalculatorBuilder builder) {
        this.brand = builder.brand;
        this.total = builder.total;
        this.items = builder.items;
    }

    private SearchPayload(Builder builder) {
        this.category = builder.category;
        this.items = builder.items;
    }


    public static class CalculatorBuilder extends AbstractBuilder<SearchPayload> {

        private String brand;
        private double total;
        public List<Payload> items = new ArrayList<>();

        public CalculatorBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CalculatorBuilder withItems(List<Payload> categories) {
            this.items = categories;
            return this;
        }


        @Override
        public SearchPayload build() {
            this.total = this.items.stream().mapToDouble(Payload::getPrice).sum();
            return new SearchPayload(this);
        }
    }

    public static class Builder extends AbstractBuilder<SearchPayload> {
        private String category;
        private List<Payload> items = new ArrayList<>();

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withItems(List<Payload> categories) {
            this.items = categories;
            return this;
        }

        @Override
        public SearchPayload build() {
            return new SearchPayload(this);
        }
    }
}

